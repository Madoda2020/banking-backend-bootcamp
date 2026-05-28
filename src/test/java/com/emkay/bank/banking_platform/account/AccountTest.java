package com.emkay.bank.banking_platform.account;

import com.emkay.bank.banking_platform.customer.Customer;
import com.emkay.bank.banking_platform.shared.domain.Money;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.Instant;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class AccountTest {

    @Test
     void createBankAccount(){
        Customer customer = new Customer(
                UUID.randomUUID()
                , "Emkay Kworld"
                , "emkay@email.com"
                , Instant.now());

        BankAccount account = new BankAccount(
                UUID.randomUUID()
                ,customer.getId()
                ,"AccNum89098"
                ,AccountType.SAVINGS
                ,AccountStatus.ACTIVE
                , Money.zar("500")
                ,Instant.now());
        assertTrue((customer.getId().toString().length() > 10) && (account.getId().toString().length() > 10));
        assertNotEquals(customer.getId(),account.getId());
        assertEquals("Emkay Kworld",customer.getFullName());
        assertEquals("AccNum89098",account.getAccountNumber());
        assertEquals(customer.getId(),account.getCustomerId());
    }

    @Test
     void rejectNegativeOpeningBalance(){

    }

    @Test
     void depositIntoActiveAccount(){

    }

    @Test
     void withdrawFromActiveAccount(){

    }

    @Test
     void rejectOverdraft(){

    }
    @Test
     void rejectDepositIntoFrozenAccount(){

    }

    @Test
     void rejectWithdrawalFromFrozenAccount(){

    }

    @Test
     void freezeActiveAccount(){

    }

    @Test
     void reactivateFrozenAccount(){

    }

    @Test
     void closeAccountWhenBalanceIsZero(){

    }

    @Test
     void rejectsClosingAccountWithNonZeroBalance(){

    }

    @Test
     void rejectsModifyingClosedAccount(){

    }
}
