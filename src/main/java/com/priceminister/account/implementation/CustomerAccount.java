package com.priceminister.account.implementation;

import com.priceminister.account.*;


public class CustomerAccount implements Account {

	private IllegalBalanceException illegalBalanceException;
	
	private Double balance;
	
	public CustomerAccount() {
		this.balance = 0d;
	}
	
    public void add(Double addedAmount) {
        balance += addedAmount;
    }

    public Double getBalance() {
        return balance;
    }

    /**
     *  I did a check to see if the customer has enough balance, 
     *  if not an IllegalBalanceException will be thrown and the balance will remain the same
     *  
     */
    public Double withdrawAndReportBalance(Double withdrawnAmount, AccountRule rule) 
    		throws IllegalBalanceException {
    	
        Double resultedBalance = balance - withdrawnAmount;
        
        if (rule.withdrawPermitted(resultedBalance)) {
        	balance = resultedBalance;
        } else {
        	illegalBalanceException = new IllegalBalanceException(resultedBalance);
        	throw illegalBalanceException;
        }
        
        return getBalance();
    }

}
