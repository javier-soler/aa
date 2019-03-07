package com.voyvas.user.endpoint;

import java.security.Principal;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.voyvas.user.model.Account;
import com.voyvas.user.model.repository.AccountRepository;

@Controller
public class MeEndpoint {

	@Autowired
	AccountRepository accRepo;

	@ResponseBody
	@RequestMapping(path = "/me", method = RequestMethod.GET)
	public Optional<Account> me(Principal p) {
		if (p != null && p instanceof Authentication) {
			final Authentication a = (Authentication) p;
			return accRepo.findByExtendedUsername(a.getName());
		}
		return Optional.empty();
	}

	@ResponseBody
	@RequestMapping(path = "/me2")
	public Principal accounts(Principal p) {
		return p;
	}

}
