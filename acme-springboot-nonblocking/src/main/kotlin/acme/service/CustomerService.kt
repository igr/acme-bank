package acme.service

import acme.dto.AccountDto
import acme.dto.CustomerDto
import acme.entity.Account
import acme.repository.AccountRepository
import acme.repository.CustomerRepository
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.toList
import org.springframework.stereotype.Service
import java.util.*

@Service
class CustomerService(
    private val customerRepository: CustomerRepository,
    private val accountRepository: AccountRepository
) {

    suspend fun getCurrentCustomer(customerId: UUID): CustomerDto? {
        return customerRepository.findById(customerId)
            ?.let { customer -> CustomerDto(customer.customerId, customer.name) }
    }

    suspend fun getCustomerAccounts(customerId: UUID): List<AccountDto> {
        // verify the customer exists
        customerRepository.findById(customerId)
            ?: throw IllegalArgumentException("Customer not found with id: $customerId")

        return accountRepository.findAccountsByCustomerId(customerId)
            .map { account -> mapAccountToDto(account) }
            .toList()
    }

    private fun mapAccountToDto(account: Account): AccountDto {
        return AccountDto(
            accountId = account.accountId,
            bankId = account.bankId,
            routingNumber = account.routingNumber,
            amount = account.amount,
            withdrawalLimit = account.withdrawalLimit,
            lastChange = account.lastChange
        )
    }

    suspend fun getTotalAccountsAmount(): Long {
        return accountRepository.getTotalAmount() ?: 0L
    }
}
