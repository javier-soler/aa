package com.voyvas.config.dev;

import java.util.Arrays;
import java.util.Optional;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;

import com.voyvas.user.model.Account;
import com.voyvas.user.model.Authority;
import com.voyvas.user.model.Profile;
import com.voyvas.user.model.Tenant;

@Configuration
public class DevInitializer implements CommandLineRunner {

	@Autowired
	private EntityManager em;

	@Autowired(required = false)
	PasswordEncoder pe;

	@Override
	@Transactional
	public void run(String... args) throws Exception {

		if (em.createQuery("select count(a) from Account a", Long.class).getSingleResult() > 1) {
			return;
		}

		// authorities
		Authority x1 = new Authority("VIEW_USER", "View User");
		Authority x2 = new Authority("DELETE_USER", "Delete User");
		Authority x3 = new Authority("CREATE_USER", "Create User");
		em.persist(x1);
		em.persist(x2);
		em.persist(x3);

		Tenant t1 = new Tenant("TA", "Tenant A", "Description Tenant A");
		Tenant t2 = new Tenant("TB", "Tenant B", "Description Tenant B");
		em.persist(t1);
		em.persist(t2);

		Profile admin = new Profile();
		admin.setTenantId(t1.getId());
		admin.setName("Admin");
		admin.setAuthorities(Arrays.asList(x1, x2, x3));
		em.persist(admin);
		em.flush();

		Account ac1 = new Account(t1.getId(), "user1",
				Optional.ofNullable(pe).orElse(NoOpPasswordEncoder.getInstance()).encode("user"), "John", "Doe",
				"john@gmail.com", 20, admin);
		Account ac2 = new Account(t1.getId(), "user2",
				Optional.ofNullable(pe).orElse(NoOpPasswordEncoder.getInstance()).encode("user"), "Jane", "Doe",
				"jane@gmail.com", 22, admin);

		em.persist(ac1);
		em.persist(ac2);
	}

}
