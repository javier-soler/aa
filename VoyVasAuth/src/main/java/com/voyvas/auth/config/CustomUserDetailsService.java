package com.voyvas.auth.config;

import javax.transaction.Transactional;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.voyvas.user.model.Account;
import com.voyvas.user.model.Authority;
import com.voyvas.user.model.repository.AccountRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService {

	@Autowired
	private AccountRepository accountRepo;

	/**
	 * Username will be in format <username>@compShortName
	 */
	@Override
	@Transactional
	public UserDetails loadUserByUsername(String un) throws UsernameNotFoundException {
		return accountRepo
				.findByExtendedUsername(un)
				.map(this::fromUser)
				.orElseThrow(() -> new UsernameNotFoundException("User not found"));
	}

	private UserDetails fromUser(Account u) {
		UserDetails user = User
				.builder()
				.username(u.getUserName() + '@' + u.getTenant().getShortName())
				.password(u.getPassword())
				.authorities(u.getProfile().getAuthorities().stream().map(Authority::getId).toArray(String[]::new))
				.build();

		return user;
	}

}
