package acme.repository;

import acme.entity.Account;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface AccountRepository extends CrudRepository<Account, UUID> {

    @Query("""
        SELECT a.* FROM account a
        JOIN customer_account ca ON a.account_id = ca.account_id
        WHERE ca.customer_id = :customerId
    """)
    List<Account> findAccountsByCustomerId(@Param("customerId") UUID customerId);

    @Query("SELECT SUM(amount) FROM account")
    Long getTotalAmount();
}
