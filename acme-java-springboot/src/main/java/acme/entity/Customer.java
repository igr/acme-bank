package acme.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Version;
import org.springframework.data.relational.core.mapping.Table;

import java.util.UUID;

@Table("customer")
public record Customer(
    @Id UUID customerId,
    String name,
    @Version Long version
) {
    public Customer(UUID customerId, String name) {
        this(customerId, name, 0L);
    }
}
