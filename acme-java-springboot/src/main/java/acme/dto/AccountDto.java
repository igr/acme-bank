package acme.dto;

import java.time.LocalDateTime;
import java.util.UUID;

public record AccountDto(
    UUID accountId,
    UUID bankId,
    String routingNumber,
    Long amount,
    Long withdrawalLimit,
    LocalDateTime lastChange
) {}
