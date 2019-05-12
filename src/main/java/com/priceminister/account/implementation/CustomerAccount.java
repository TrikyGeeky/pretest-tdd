package com.priceminister.account.implementation;

import com.priceminister.account.*;


public class CustomerAccount implements Account {

	private Double balance;
	private IllegalBalanceException illegalBalanceException;
	
	public CustomerAccount() {
		this.balance = 0d;
	}
	
    public void add(Double addedAmount) {
        balance += addedAmount;
    }

    public Double getBalance() {
        return balance;
    }

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
