package com.emkay.bank.banking_platform.customer;

import java.time.Instant;
import java.util.Objects;
import java.util.UUID;

public final class Customer {

    private final UUID id;
    private final String fullName;
    private final String email;
    private final Instant createdAt;

    public Customer(UUID id, String fullName, String email, Instant createdAt) {
        if (id == null) {
            throw new IllegalArgumentException("Customer ID is required");
        }
        if (fullName == null || fullName.isBlank()) {
            throw new IllegalArgumentException("Customer full name is required");
        }
        if (email == null || email.isBlank()) {
            throw new IllegalArgumentException("Customer email is required");
        }
        if (!email.contains("@")) {
            throw new IllegalArgumentException("Customer email is invalid");
        }
        if (createdAt == null) {
            throw new IllegalArgumentException("Customer creation time is required");
        }

        this.id = id;
        this.fullName = fullName.trim();
        this.email = email.trim().toLowerCase();
        this.createdAt = createdAt;
    }

    public UUID getId() {
        return id;
    }

    public String getFullName() {
        return fullName;
    }

    public String getEmail() {
        return email;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Customer customer)) {
            return false;
        }
        return id.equals(customer.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
