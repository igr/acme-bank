package acme.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Version;
import org.springframework.data.relational.core.mapping.Table;

import java.util.UUID;

@Table("bank")
public record Bank(
    @Id UUID bankId,
    String name,
    String address,
    @Version Long version
) {
    public Bank(UUID bankId, String name, String address) {
        this(bankId, name, address, 0L);
    }
}
