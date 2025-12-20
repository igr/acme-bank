package acme.entity;

import org.springframework.data.relational.core.mapping.Table;

import java.util.UUID;

@Table("customer_account")
public record CustomerAccount(
    UUID customerId,
    UUID accountId
) {}
