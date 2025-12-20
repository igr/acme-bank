package acme.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Version;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDateTime;
import java.util.UUID;

@Table("account")
public record Account(
    @Id UUID accountId,
    UUID bankId,
    String routingNumber,
    Long amount,
    Long withdrawalLimit,
    LocalDateTime lastChange,
    @Version Long version
) {
    public Account(UUID accountId, UUID bankId, String routingNumber, Long amount,
                   Long withdrawalLimit, LocalDateTime lastChange) {
        this(accountId, bankId, routingNumber, amount, withdrawalLimit, lastChange, null);
    }

    public Account withAmount(Long newAmount) {
        return new Account(accountId, bankId, routingNumber, newAmount, withdrawalLimit, LocalDateTime.now(), version);
    }
}
