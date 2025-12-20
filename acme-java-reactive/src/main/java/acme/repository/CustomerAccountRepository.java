package acme.repository;

import acme.entity.CustomerAccount;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

import java.util.UUID;

@Repository
public interface CustomerAccountRepository extends ReactiveCrudRepository<CustomerAccount, UUID> {
    Flux<CustomerAccount> findByCustomerId(UUID customerId);
}
