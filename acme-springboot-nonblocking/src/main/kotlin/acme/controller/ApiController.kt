package acme.controller

import acme.dto.AccountDto
import acme.dto.CustomerDto
import acme.dto.TransactionRequest
import acme.entity.Transaction
import acme.service.CustomerService
import acme.service.TransactionService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.util.*

@RestController
@RequestMapping("/api")
class ApiController(
    private val customerService: CustomerService,
    private val transactionService: TransactionService
) {

    @GetMapping("/customers/{customerId}")
    suspend fun getCurrentCustomer(@PathVariable customerId: UUID): ResponseEntity<CustomerDto> {
        val customer = customerService.getCurrentCustomer(customerId)
        return if (customer != null) {
            ResponseEntity.ok(customer)
        } else {
            ResponseEntity.notFound().build()
        }
    }

    @GetMapping("/customers/{customerId}/accounts")
    suspend fun getCustomerAccounts(@PathVariable customerId: UUID): ResponseEntity<List<AccountDto>> {
        return try {
            val accounts = customerService.getCustomerAccounts(customerId)
            ResponseEntity.ok(accounts)
        } catch (_: IllegalArgumentException) {
            ResponseEntity.notFound().build()
        }
    }

    @PostMapping("/transactions")
    suspend fun performTransaction(@RequestBody request: TransactionRequest): ResponseEntity<Any> {
        val response = transactionService.performTransaction(request)
        return ResponseEntity.ok(response)
    }

    @GetMapping("/accounts/{accountId}/transactions")
    suspend fun getTransactionHistory(@PathVariable accountId: UUID): ResponseEntity<List<Transaction>> {
        val transactions = transactionService.getTransactionHistory(accountId)
        return ResponseEntity.ok(transactions)
    }

    @GetMapping("/accounts/total-amount")
    suspend fun getTotalAccountsAmount(): ResponseEntity<Map<String, Long>> {
        val totalAmount = customerService.getTotalAccountsAmount()
        return ResponseEntity.ok(mapOf("totalAmount" to totalAmount))
    }
}
