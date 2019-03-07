package com.voyvas.user.model.repository;

import java.security.Principal;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.core.annotation.HandleBeforeSave;
import org.springframework.data.rest.core.annotation.RepositoryEventHandler;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.voyvas.user.model.Account;

@RepositoryEventHandler({ Account.class })
public class AccountHandler {

	@Autowired
	private PasswordEncoder pe;

	@HandleBeforeSave
	public void prepareAccount(Account account) {
		if (account != null && StringUtils.isNotEmpty(account.getPlainTextPassword())) {
			account.setPassword(pe.encode(account.getPlainTextPassword()));
		}
	}
}
