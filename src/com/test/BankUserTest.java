package com.test;

import com.acmebank.BankUser;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.math.BigDecimal;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;


class BankUserTest {

    private BankUser bankUser;

    @BeforeEach
    public void setup() {
        bankUser = new BankUser(1000000);
    }

    @Test
    public void floatingNumberTest(){
        bankUser.setBalance(new BigDecimal(0));
        bankUser.deposit(0.2);
        assertEquals(0.2, bankUser.getBalance().doubleValue(),0);
        bankUser.deposit(0.1);
        assertNotEquals(0.30000000000000004 , bankUser.getBalance().doubleValue(),0);
    }

    @Test
    public void multiThreadTest() {

        Thread thread1 = new Thread(() -> {
            for (int i = 0; i < 10000000; i++) {
                bankUser.deposit(1);
            }
        });

        Thread thread2 = new Thread(() -> {
            for (int i = 0; i < 10000000; i++) {
                bankUser.withdraw(1);
            }
        });

        thread1.start();
        thread2.start();


        assertEquals(1000000, bankUser.getBalance().doubleValue(), 0);


    }

    @Test
    public void testExceptions() {
        assertThrows(Exception.class, () -> bankUser.deposit(-100));
        assertThrows(Exception.class, () -> bankUser.deposit(Integer.parseInt("adafsdf")));
        assertThrows(Exception.class, () -> bankUser.withdraw(-100));
        assertThrows(Exception.class, () -> bankUser.withdraw(1000000000));
        assertThrows(Exception.class, () -> bankUser.withdraw(Integer.parseInt("adafsdf")));
    }


    @ParameterizedTest
    @MethodSource("depositValues")
    void deposit(double amount, double expected) {
        bankUser.deposit(amount);
        assertEquals(expected, bankUser.getBalance().doubleValue(), 0);
    }

    private static Stream<Arguments> depositValues() {
        return Stream.of(
                Arguments.of(1000000, 2000000),
                Arguments.of(1, 1000001)
        );
    }

    @ParameterizedTest
    @MethodSource("withdrawValues")
    void withdraw(double amount, double expected) {
        bankUser.withdraw(amount);
        assertEquals(expected, bankUser.getBalance().doubleValue(), 0);
    }

    private static Stream<Arguments> withdrawValues() {
        return Stream.of(
                Arguments.of(1000000, 0),
                Arguments.of(1, 999999)
        );
    }
}