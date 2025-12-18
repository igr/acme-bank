package acme.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Version;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDateTime;
import java.util.UUID;

@Table("transaction")
public record Transaction(
    @Id UUID transactionId,
    UUID fromAccountId,
    UUID toAccountId,
    Long amount,
    LocalDateTime timestamp,
    String status,
    @Version Long version
) {
    public Transaction(UUID transactionId, UUID fromAccountId, UUID toAccountId,
                       Long amount, LocalDateTime timestamp, String status) {
        this(transactionId, fromAccountId, toAccountId, amount, timestamp, status, 0L);
    }
}
