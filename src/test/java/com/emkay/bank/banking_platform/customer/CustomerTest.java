package com.emkay.bank.banking_platform.customer;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.Instant;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
public class CustomerTest {

    @Test
    void createCustomer() {
        Customer customer = new Customer(
                UUID.randomUUID()
                , "Emkay Kworld"
                , "emkay@email.com"
                , Instant.now());
        assertEquals("Emkay Kworld",customer.getFullName());
        assertEquals("emkay@email.com", customer.getEmail());
        }

    @Test
     void rejectNullId(){
        assertThrows(IllegalArgumentException.class, () -> new Customer(null,"Test name","test@mail.com",Instant.now()));
   }

   @Test
    void rejectBlankFullName(){
       assertThrows(IllegalArgumentException.class, () -> new Customer(UUID.randomUUID(),"","test@mail.com",Instant.now()));
   }

    @Test
    void rejectBlankEmail(){
        assertThrows(IllegalArgumentException.class, () -> new Customer(UUID.randomUUID(),"Emkay Test","",Instant.now()));
    }
    @Test
    void rejectInvalidEmail(){
        assertThrows(IllegalArgumentException.class, () -> new Customer(UUID.randomUUID(),"test user","test9mail.com",Instant.now()));
    }

    @Test
    void nomalizeEmail() {
        Customer customer = new Customer(
                UUID.randomUUID()
                , "Emkay Kworld"
                , "EMkaY@EMaIl.cOMm"
                , Instant.now());
        assertEquals("emkay@email.comm", customer.getEmail());
    }

    @Test
    void trimFullName() {
        Customer customer = new Customer(
                UUID.randomUUID()
                , " Emkay Kworld "
                , "EMkaY@EMaIl.cOMm"
                , Instant.now());
        assertEquals("Emkay Kworld", customer.getFullName());
    }
}
