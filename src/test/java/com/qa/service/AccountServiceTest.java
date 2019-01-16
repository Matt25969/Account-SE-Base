package com.qa.service;

import static org.junit.Assert.*;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.qa.domain.Account;
import com.qa.util.JSONUtil;

public class AccountServiceTest {

	private AccountService service;
	private Account joeBloggs;
	private Account janeBloggs;
	private JSONUtil util;

	@Before
	public void setup() {
		service = new AccountService();
		joeBloggs = new Account("Joe", "Bloggs", "1234");
		janeBloggs = new Account("Jane", "Bloggs", "1234");
		util = new JSONUtil();
	}
	
	@Test
	public void addAccountTest() {
		service.addAccountFromMap(joeBloggs);
		assertEquals(service.getAccountMap().size(), 1);
	}
	
	@Test
	public void addAccountTest2() {
		service.addAccountFromMap(joeBloggs);
		service.addAccountFromMap(janeBloggs);
		assertEquals(service.getAccountMap().size(), 2);
	}

	@Test
	public void removeAccountTest() {
		service.addAccountFromMap(joeBloggs);
		service.addAccountFromMap(janeBloggs);
		service.removeAccountFromMap(0);
		assertEquals(service.getAccountMap().size(), 1);
	}
	
	@Test
	public void removeAccountTest2() {
		service.addAccountFromMap(joeBloggs);
		service.addAccountFromMap(janeBloggs);
		service.removeAccountFromMap(0);
		service.removeAccountFromMap(1);
		Assert.assertEquals(service.getAccountMap().size(), 0);
	}
	
	@Test
	public void removeAccountTest3() {
		service.addAccountFromMap(joeBloggs);
		service.addAccountFromMap(janeBloggs);
		service.removeAccountFromMap(0);
		service.removeAccountFromMap(1);
		service.removeAccountFromMap(5);
		Assert.assertEquals(service.getAccountMap().size(), 0);
	}
	
	@Test
	public void accountConversionToJSONTestEmpty() {
		String emptyMap = util.getJSONForObject(service.getAccountMap());
		Assert.assertEquals("{}", emptyMap);
	}
	
	@Test
	public void accountConversionToJSONTestEmptyWithConversion() {
		String emptyMap = util.getJSONForObject(service.getAccountMap());
		String accountAsJSON = "{\"0\":{\"firstName\":\"Joe\",\"secondName\":\"Bloggs\",\"accountNumber\":\"1234\"},\"1\":{\"firstName\":\"Jane\",\"secondName\":\"Bloggs\",\"accountNumber\":\"1234\"}}";
		Assert.assertEquals("{}", emptyMap);
	}

	@Test
	public void accountConversionToJSONTest() {
		String accountAsJSON = "{\"0\":{\"firstName\":\"Joe\",\"secondName\":\"Bloggs\",\"accountNumber\":\"1234\"},\"1\":{\"firstName\":\"Jane\",\"secondName\":\"Bloggs\",\"accountNumber\":\"1234\"}}";
		service.addAccountFromMap(joeBloggs);
		service.addAccountFromMap(janeBloggs);
		String populatedAccountMap = util.getJSONForObject(service.getAccountMap());
		Assert.assertEquals(accountAsJSON, populatedAccountMap);
	}

	@Test
	public void getCountForFirstNamesInAccountWhenZero() {
		Assert.assertEquals(service.getNumberOfAccountWithFirstName("Joe"), 0);
	}
	
	@Test
	public void getCountForFirstNamesInAccountWhenOne() {
		service.addAccountFromMap(joeBloggs);
		Assert.assertEquals(service.getNumberOfAccountWithFirstName("Joe"), 1);
		
	}

	@Test
	public void getCountForFirstNamesInAccountWhenMult() {
		service.addAccountFromMap(joeBloggs);
		Account joeGordon = new Account("Joe", "Gordon", "1234");
		service.addAccountFromMap(joeGordon);
		Assert.assertEquals(2, service.getNumberOfAccountWithFirstName("Joe"));
	}

}
