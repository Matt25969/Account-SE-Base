package com.qa.persistence.repository;

import java.util.HashMap;
import java.util.Map;

import com.qa.persistence.domain.Account;

public class AccountMapRepository implements AccountRepository{
	
	Map<Long, Account> account = new HashMap<>();

	public String getAllAccounts() {
		// TODO Auto-generated method stub
		return null;
	}

	public String createAccount(String account) {
		// TODO Auto-generated method stub
		return null;
	}

	public String deleteAccount(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	public String updateAccount(Long id, String account) {
		// TODO Auto-generated method stub
		return null;
	}

}
