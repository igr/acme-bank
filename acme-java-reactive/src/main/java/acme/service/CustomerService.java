package acme.service;

import acme.dto.AccountDto;
import acme.dto.CustomerDto;
import acme.entity.Account;
import acme.repository.AccountRepository;
import acme.repository.CustomerRepository;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Service
public class CustomerService {

    private final CustomerRepository customerRepository;
    private final AccountRepository accountRepository;

    public CustomerService(CustomerRepository customerRepository, AccountRepository accountRepository) {
        this.customerRepository = customerRepository;
        this.accountRepository = accountRepository;
    }

    public Mono<CustomerDto> getCurrentCustomer(UUID customerId) {
        return customerRepository.findById(customerId)
            .map(customer -> new CustomerDto(customer.customerId(), customer.name()));
    }

    public Flux<AccountDto> getCustomerAccounts(UUID customerId) {
        return customerRepository.findById(customerId)
            .switchIfEmpty(Mono.error(new IllegalArgumentException("Customer not found with id: " + customerId)))
            .flatMapMany(customer -> accountRepository.findAccountsByCustomerId(customerId))
            .map(this::mapAccountToDto);
    }

    private AccountDto mapAccountToDto(Account account) {
        return new AccountDto(
            account.accountId(),
            account.bankId(),
            account.routingNumber(),
            account.amount(),
            account.withdrawalLimit(),
            account.lastChange()
        );
    }

    public Mono<Long> getTotalAccountsAmount() {
        return accountRepository.getTotalAmount()
            .defaultIfEmpty(0L);
    }
}
