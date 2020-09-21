package com.test;

import com.acmebank.Bank;
import com.acmebank.BankUser;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BankTest {
    private Bank bank;
    private Bank bank2;
    private BankUser bankUser1;
    private BankUser bankUser2;

    @BeforeEach
    public void setup() {
        bank = new Bank();
        bank2 = new Bank();
        bankUser1 = new BankUser(1000);
        bankUser2 = new BankUser(0);
    }

    @Test()
    void testExceptions(){
        bank.setActive(false);
        assertThrows(Exception.class, () -> bank.addNewUser(bankUser2));
        assertThrows(Exception.class, () -> bank.removeUser(bankUser2));
    }

    @Test
    void addNewUser() throws Exception {

        bank.addNewUser(bankUser1);
        bank2.setActive(false);

        assertTrue(bank.isActive());
        assertTrue(bank.getUserList().contains(bankUser1));
        assertFalse(bank.getUserList().contains(bankUser2));
        assertFalse(bank2.isActive());

    }

    @Test
    void removeUser() throws Exception {
        assertNull(bankUser1.getBank());

        bank.addNewUser(bankUser1);
        assertNotNull(bankUser1.getBank());

        bank.removeUser(bankUser1);
        assertFalse(bank.getUserList().contains(bankUser1));
        assertNull(bankUser1.getBank());
    }

    @Test
    void getUserList() throws Exception {
        bank.addNewUser(bankUser1);
        bank2.addNewUser(bankUser2);
        assertArrayEquals(new BankUser[]{bankUser1}, bank.getUserList().toArray());
        assertArrayEquals(new BankUser[]{bankUser2}, bank2.getUserList().toArray());
    }

}