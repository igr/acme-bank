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

import java.util.List;
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
    public ResponseEntity<CustomerDto> getCurrentCustomer(@PathVariable UUID customerId) {
        CustomerDto customer = customerService.getCurrentCustomer(customerId);
        if (customer != null) {
            return ResponseEntity.ok(customer);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/customers/{customerId}/accounts")
    public ResponseEntity<List<AccountDto>> getCustomerAccounts(@PathVariable UUID customerId) {
        try {
            List<AccountDto> accounts = customerService.getCustomerAccounts(customerId);
            return ResponseEntity.ok(accounts);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/transactions")
    public ResponseEntity<TransactionResponse> performTransaction(@RequestBody TransactionRequest request) {
        TransactionResponse response = transactionService.performTransaction(request);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/accounts/{accountId}/transactions")
    public ResponseEntity<List<Transaction>> getTransactionHistory(@PathVariable UUID accountId) {
        List<Transaction> transactions = transactionService.getTransactionHistory(accountId);
        return ResponseEntity.ok(transactions);
    }

    @GetMapping("/accounts/total-amount")
    public ResponseEntity<Map<String, Long>> getTotalAccountsAmount() {
        Long totalAmount = customerService.getTotalAccountsAmount();
        return ResponseEntity.ok(Map.of("totalAmount", totalAmount));
    }
}
