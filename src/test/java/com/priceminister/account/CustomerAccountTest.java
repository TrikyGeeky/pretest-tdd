package com.priceminister.account;

import static org.junit.Assert.*;
import static org.junit.Assume.assumeNoException;

import org.junit.*;

import com.priceminister.account.implementation.*;

/**
 * Please create the business code, starting from the unit tests below.
 * Implement the first test, then develop the code that makes it pass. Then
 * focus on the second test, and so on.
 * 
 * We want to see how you "think code", and how you organize and structure a
 * simple application.
 * 
 * When you are done, please zip the whole project (incl. source-code) and send
 * it to recrutement-dev@priceminister.com
 * 
 */
public class CustomerAccountTest {

	Account customerAccount;
	AccountRule rule;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		customerAccount = new CustomerAccount();
	}

	/**
	 * Tests that an empty account always has a balance of 0.0, not a NULL.
	 */
	@Test
	public void testAccountWithoutMoneyHasZeroBalance() {
		assertTrue("verify that empty balance equals 0 and not null", customerAccount.getBalance() == 0d);
	}

	/**
	 * Adds money to the account and checks that the new balance is as expected.
	 */
	@Test
	public void testAddPositiveAmount() {
		customerAccount.add(400d);
		assertTrue("verify that the new balance is what's expected", customerAccount.getBalance() == 400d);
	}

	/**
	 * Tests that an illegal withdrawal throws the expected exception. Use the logic
	 * contained in CustomerAccountRule; feel free to refactor the existing code.
	 * 
	 * @throws IllegalBalanceException
	 */
	@Test(expected = IllegalBalanceException.class)
	public void testWithdrawAndReportBalanceIllegalBalance() throws IllegalBalanceException {
		rule = new CustomerAccountRule();
		customerAccount.withdrawAndReportBalance(30d, rule);
	}
	
	/**
	 * Tests that an illegal withdrawal does not change the customer balance.
	 * 
	 */
	@Test
	public void testWithdrawAndBalanceNotChanged() {
		rule = new CustomerAccountRule();
		Double initialBalance = customerAccount.getBalance();
		try {
			customerAccount.withdrawAndReportBalance(30d, rule);
		} catch (IllegalBalanceException e) {
			System.out.println(e.toString());
		} finally {
			assertTrue("verify that Balance hasn't changed after illegal withdraw", 
					customerAccount.getBalance() == initialBalance);
		}
	}

	/**
	 * Tests that a withdraw has been done without raising an
	 * IllegalBalanceException
	 * 
	 */
	@Test
	public void testSuccessfulWithdraw() throws IllegalBalanceException {
		rule = new CustomerAccountRule();
		customerAccount.add(100d);
		
		customerAccount.withdrawAndReportBalance(30d, rule);
		
		assertTrue("verify that the balance has been updated after succesful withdraw", 
				customerAccount.getBalance() == 70d);
	}

}
