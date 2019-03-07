package com.voyvas.user.model.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.voyvas.user.model.Account;
import com.voyvas.user.model.repository.custom.CustomAccountRepository;

public interface AccountRepository extends JpaRepository<Account, Long>, CustomAccountRepository {

	Optional<Account> findByUserName(String userName);
	
	List<Account> findByFirstName(String firstName);
	
	List<Account> findByFirstNameContaining(String name);
}
