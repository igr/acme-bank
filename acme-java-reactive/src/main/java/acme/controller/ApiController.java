package acme.controller;

import acme.dto.AccountDto;
import acme.dto.CustomerDto;
import acme.dto.TransactionRequest;
import acme.dto.TransactionResponse;
import acme.entity.Transaction;
import acme.service.CustomerService;
import acme.service.TransactionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/api")
public class ApiController {

    private final CustomerService customerService;
    private final TransactionService transactionService;

    public ApiController(CustomerService customerService, TransactionService transactionService) {
        this.customerService = customerService;
        this.transactionService = transactionService;
    }

    @GetMapping("/customers/{customerId}")
    public Mono<ResponseEntity<CustomerDto>> getCurrentCustomer(@PathVariable UUID customerId) {
        return customerService.getCurrentCustomer(customerId)
            .map(ResponseEntity::ok)
            .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @GetMapping("/customers/{customerId}/accounts")
    public Mono<ResponseEntity<Flux<AccountDto>>> getCustomerAccounts(@PathVariable UUID customerId) {
        return Mono.just(ResponseEntity.ok(customerService.getCustomerAccounts(customerId)));
    }

    @PostMapping("/transactions")
    public Mono<ResponseEntity<TransactionResponse>> performTransaction(@RequestBody TransactionRequest request) {
        return transactionService.performTransaction(request)
            .map(ResponseEntity::ok);
    }

    @GetMapping("/accounts/{accountId}/transactions")
    public Mono<ResponseEntity<Flux<Transaction>>> getTransactionHistory(@PathVariable UUID accountId) {
        return Mono.just(ResponseEntity.ok(transactionService.getTransactionHistory(accountId)));
    }

    @GetMapping("/accounts/total-amount")
    public Mono<ResponseEntity<Map<String, Long>>> getTotalAccountsAmount() {
        return customerService.getTotalAccountsAmount()
            .map(totalAmount -> ResponseEntity.ok(Map.of("totalAmount", totalAmount)));
    }
}
