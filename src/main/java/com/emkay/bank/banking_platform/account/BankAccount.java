package com.emkay.bank.banking_platform.account;

import com.emkay.bank.banking_platform.shared.domain.Money;

import java.time.Instant;
import java.util.Objects;
import java.util.UUID;

public class BankAccount {
    private final UUID id;
    private final UUID customerId;
    private final String accountNumber;
    private final AccountType accountType;
    private AccountStatus accountStatus;
    private Money balance;
    private final Instant createdAt;

    public BankAccount(
            UUID id,
            UUID customerId,
            String accountNumber,
            AccountType accountType,
            AccountStatus accountStatus,
            Money openingBalance,
            Instant createdAt
    ){
        if(id == null){
            throw new IllegalArgumentException("Account ID is required");
        }
        if(customerId == null){
            throw new IllegalArgumentException("Customer ID is required");
        }
        if(accountNumber == null){
            throw new IllegalArgumentException("Account number is required");
        }
        if(accountType == null){
            throw new IllegalArgumentException("Account type is required");
        }
        if(accountStatus == null){
            throw new IllegalArgumentException("Account status is required");
        }
        if(openingBalance == null){
            throw new IllegalArgumentException("Opening balance is required");
        }
        if(createdAt ==  null){
            throw new IllegalArgumentException("Creation time is required");
        }
        this.id = id;
        this.customerId = customerId;
        this.accountNumber = accountNumber;
        this.accountType = accountType;
        this.accountStatus = accountStatus;
        this.balance = openingBalance;
        this.createdAt = createdAt;
    }
    public void deposit(Money amount){
        ensureActive();
        validatePositiveAmount(amount);
        this.balance = this.balance.add(amount);
    }
    public void withdraw(Money amount){
        ensureActive();
        validatePositiveAmount(amount);
        Money newBalance = this.balance.subtract(amount);
        if(newBalance.isNegative()){
            throw new IllegalArgumentException("Insufficient funds");
        }
        this.balance = newBalance;
    }
    public void freeze(){
        ensureNotClosed();
        this.accountStatus = AccountStatus.FROZEN;
    }
    public void activate(){
        ensureNotClosed();
        this.accountStatus = AccountStatus.ACTIVE;
    }
    public void close(){
        if(!balance.isZero()){
            throw new IllegalArgumentException("Account balance must be zero before closing");
        }
        this.accountStatus = AccountStatus.CLOSED;
    }
    private void ensureActive(){
        if(accountStatus != AccountStatus.ACTIVE){
            throw new IllegalArgumentException("Account is not active");
        }
    }
    private void ensureNotClosed(){
        if(accountStatus == AccountStatus.CLOSED){
            throw new IllegalArgumentException("Closed account cannot be modified");
        }
    }
    private void validatePositiveAmount(Money amount){
        if(amount == null){
            throw new IllegalArgumentException("Amount is required");
        }
        if(amount.isNegative() || amount.isZero()){
            throw new IllegalArgumentException("Amount must be positive");
        }
    }
    public UUID getId() {
        return id;
    }

    public UUID getCustomerId() {
        return customerId;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public AccountStatus getAccountStatus() {
        return accountStatus;
    }

    public AccountType getAccountType() {
        return accountType;
    }

    public Money getBalance() {
        return balance;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }
    @Override
    public boolean equals(Object o) {
        if (!(o instanceof BankAccount that)) {
            return false;
        }
        return id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

}
