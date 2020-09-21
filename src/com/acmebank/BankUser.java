package com.acmebank;

import java.math.BigDecimal;
import java.math.MathContext;

public class BankUser {

    private BigDecimal balance;
    private Bank bank;
    private MathContext mathContext;

    public BankUser(double initialBalance) {

        mathContext = new MathContext(10);

        if (initialBalance >= 0) {
            this.balance = new BigDecimal(String.valueOf(initialBalance));
        } else {
            this.balance = new BigDecimal("0");
        }
    }

    public void deposit(double amount) {

        if (amount > 0) {
            balance = balance.add(new BigDecimal(String.valueOf(amount)),mathContext);
        } else {
            throw new NumberFormatException("Please enter valid amount");
        }


    }

    public synchronized void withdraw(double amount) {

        if (amount > 0 && amount <= balance.doubleValue()) {
            balance = balance.subtract(new BigDecimal(String.valueOf(amount)),mathContext);
        } else {
            throw new NumberFormatException("Please enter valid amount");
        }
    }


    public BigDecimal getBalance() {
        return balance;
    }


    public Bank getBank() {
        return bank;
    }

    public void setBank(Bank bank) {
        this.bank = bank;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }
}
