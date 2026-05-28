package com.emkay.bank.banking_platform.shared.domain;

import org.junit.Test;

import java.util.Currency;

import static org.junit.Assert.*;

public class MoneyTest {

    @Test
    public void addsMoneyWithSameCurrency() {
        Money result = Money.zar("100.00").add(Money.zar("50.00"));

        assertEquals(Money.zar("150.00"), result);
    }

    @Test
    public void subtractsMoneyWithSameCurrency() {
        Money result = Money.zar("100.00").subtract(Money.zar("30.00"));

        assertEquals(Money.zar("70.00"), result);
    }

    @Test
    public void rejectsDifferentCurrencies() {
        Money zar = Money.zar("100.00");
        Money usd = new Money(new java.math.BigDecimal("10.00"), Currency.getInstance("USD"));

        try {
            zar.add(usd);
            fail("Expected exception");
        } catch (IllegalArgumentException ex) {
            assertEquals("Cannot operate on different currencies", ex.getMessage());
        }
    }

    @Test
    public void detectsNegativeMoney() {
        assertTrue(Money.zar("-1.00").isNegative());
        assertFalse(Money.zar("0.00").isNegative());
        assertFalse(Money.zar("1.00").isNegative());
    }

    @Test
    public void roundsUsingBankersRounding() {
        Money money = Money.zar("10.005");

        assertEquals(Money.zar("10.00"), money);
    }
}