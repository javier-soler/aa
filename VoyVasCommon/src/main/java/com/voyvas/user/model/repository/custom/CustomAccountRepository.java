package com.voyvas.user.model.repository.custom;

import java.util.Optional;

import com.voyvas.user.model.Account;

public interface CustomAccountRepository {
	Optional<Account> findByExtendedUsername(String extendedUserName);
}
