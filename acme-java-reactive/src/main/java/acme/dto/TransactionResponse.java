package acme.dto;

import java.time.LocalDateTime;
import java.util.UUID;

public record TransactionResponse(
    UUID transactionId,
    UUID fromAccountId,
    UUID toAccountId,
    Long amount,
    LocalDateTime timestamp,
    String status
) {
    public TransactionResponse(UUID transactionId, UUID fromAccountId, UUID toAccountId, Long amount) {
        this(transactionId, fromAccountId, toAccountId, amount, LocalDateTime.now(), "SUCCESS");
    }
}
