package acme.dto;

import java.util.UUID;

public record TransactionRequest(
    UUID from,
    UUID to,
    Long amount
) {}
