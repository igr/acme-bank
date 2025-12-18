package acme.service;

import acme.dto.AccountDto;
import acme.dto.CustomerDto;
import acme.entity.Account;
import acme.repository.AccountRepository;
import acme.repository.CustomerRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class CustomerService {

    private final CustomerRepository customerRepository;
    private final AccountRepository accountRepository;

    public CustomerService(CustomerRepository customerRepository, AccountRepository accountRepository) {
        this.customerRepository = customerRepository;
        this.accountRepository = accountRepository;
    }

    public CustomerDto getCurrentCustomer(UUID customerId) {
        return customerRepository.findById(customerId)
            .map(customer -> new CustomerDto(customer.customerId(), customer.name()))
            .orElse(null);
    }

    public List<AccountDto> getCustomerAccounts(UUID customerId) {
        // verify the customer exists
        customerRepository.findById(customerId).orElseThrow(() ->
            new IllegalArgumentException("Customer not found with id: " + customerId)
        );

        return accountRepository.findAccountsByCustomerId(customerId)
            .stream()
            .map(this::mapAccountToDto)
            .toList();
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

    public Long getTotalAccountsAmount() {
        Long total = accountRepository.getTotalAmount();
        return total != null ? total : 0L;
    }
}
