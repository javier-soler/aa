package com.voyvas.user.model.repository.custom;

import java.util.Optional;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.voyvas.user.model.Account;
import com.voyvas.user.model.repository.AccountRepository;

public class AccountRepositoryImpl implements CustomAccountRepository {
	@Autowired
	private AccountRepository accountRepo;

	@Override
	public Optional<Account> findByExtendedUsername(String extendedUserName) {
		if (StringUtils.isEmpty(extendedUserName)) {
			return Optional.empty();
		}
		final String[] parts = extendedUserName.split("@");
		if (parts.length != 2 || StringUtils.isEmpty(parts[0]) || StringUtils.isEmpty(parts[1])) {
			return Optional.empty();
		}
		final String user = parts[0];
		final String sn = parts[1];
		return accountRepo
				.findByUserName(user)
				.filter(a -> sn.equals(a.getTenant().getShortName()));
	}

}
