package com.acmebank;

import java.util.ArrayList;
import java.util.List;

public class Bank {

    private final List<BankUser> bankUserList;
    private boolean isActive;

    public Bank() {
        this.bankUserList = new ArrayList<>();
        this.isActive = true;
    }

    public void addNewUser(BankUser bankUser) throws Exception {
        if (isActive) {
            bankUserList.add(bankUser);
            bankUser.setBank(this);
        } else {
            throw new Exception("This branch is currently closed");
        }
    }

    public void removeUser(BankUser bankUser) throws Exception {
        if (isActive) {
            bankUserList.remove(bankUser);
            bankUser.setBank(null);
        } else {
            throw new Exception("This branch is currently closed");
        }
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public List<BankUser> getUserList() {
        return bankUserList;
    }
}
