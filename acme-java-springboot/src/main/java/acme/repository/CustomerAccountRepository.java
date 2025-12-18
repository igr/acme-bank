package acme.repository;

import acme.entity.CustomerAccount;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface CustomerAccountRepository extends CrudRepository<CustomerAccount, UUID> {
    List<CustomerAccount> findByCustomerId(UUID customerId);
}
