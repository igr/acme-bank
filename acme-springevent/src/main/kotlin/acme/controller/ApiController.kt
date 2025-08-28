package acme.controller

import acme.dto.AccountDto
import acme.dto.CustomerDto
import acme.dto.TransactionRequest
import acme.entity.Transaction

import acme.service.CustomerService
import acme.service.EventDrivenTransactionService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.util.*

@RestController
@RequestMapping("/api")
class ApiController(
    private val customerService: CustomerService,
    private val transactionService: EventDrivenTransactionService
) {

    @GetMapping("/customers/{customerId}")
    fun getCurrentCustomer(@PathVariable customerId: UUID): ResponseEntity<CustomerDto> {
        val customer = customerService.getCurrentCustomer(customerId)
        return if (customer != null) {
            ResponseEntity.ok(customer)
        } else {
            ResponseEntity.notFound().build()
        }
    }

    @GetMapping("/customers/{customerId}/accounts")
    fun getCustomerAccounts(@PathVariable customerId: UUID): ResponseEntity<List<AccountDto>> {
        return try {
            val accounts = customerService.getCustomerAccounts(customerId)
            ResponseEntity.ok(accounts)
        } catch (_: IllegalArgumentException) {
            ResponseEntity.notFound().build()
        }
    }

    @PostMapping("/transactions")
    fun performTransaction(@RequestBody request: TransactionRequest): ResponseEntity<Any> {
        return try {
            val response = transactionService.performTransaction(request).get()
            ResponseEntity.ok(response)
        } catch (e: Exception) {
            ResponseEntity.badRequest().body(mapOf("error" to (e.cause?.message ?: e.message)))
        }
    }

    @GetMapping("/accounts/{accountId}/transactions")
    fun getTransactionHistory(@PathVariable accountId: UUID): ResponseEntity<List<Transaction>> {
        val transactions = transactionService.getTransactionHistory(accountId)
        return ResponseEntity.ok(transactions)
    }

    @GetMapping("/accounts/total-amount")
    fun getTotalAccountsAmount(): ResponseEntity<Map<String, Long>> {
        val totalAmount = customerService.getTotalAccountsAmount()
        return ResponseEntity.ok(mapOf("totalAmount" to totalAmount))
    }
}
