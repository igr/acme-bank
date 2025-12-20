package acme.repository;

import acme.entity.Account;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Repository
public interface AccountRepository extends ReactiveCrudRepository<Account, UUID> {

    @Query("""
        SELECT a.* FROM account a
        JOIN customer_account ca ON a.account_id = ca.account_id
        WHERE ca.customer_id = :customerId
    """)
    Flux<Account> findAccountsByCustomerId(UUID customerId);

    @Query("SELECT SUM(amount) FROM account")
    Mono<Long> getTotalAmount();
}
