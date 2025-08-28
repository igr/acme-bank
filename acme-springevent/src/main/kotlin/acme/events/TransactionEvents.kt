package acme.events

import acme.entity.Account
import acme.entity.Transaction
import java.time.Instant
import java.util.*

sealed class TransactionEvent {
    abstract val correlationId: String
    abstract val timestamp: Instant
}

data class TransactionRequested(
    override val correlationId: String,
    override val timestamp: Instant = Instant.now(),
    val fromAccountId: UUID,
    val toAccountId: UUID,
    val amount: Long
) : TransactionEvent()

data class AccountValidationRequested(
    override val correlationId: String,
    override val timestamp: Instant = Instant.now(),
    val fromAccountId: UUID,
    val toAccountId: UUID
) : TransactionEvent()

data class AccountValidationCompleted(
    override val correlationId: String,
    override val timestamp: Instant = Instant.now(),
    val fromAccount: Account?,
    val toAccount: Account?,
    val isValid: Boolean,
    val errorMessage: String? = null
) : TransactionEvent()

data class FundsValidationRequested(
    override val correlationId: String,
    override val timestamp: Instant = Instant.now(),
    val fromAccount: Account,
    val requestedAmount: Long
) : TransactionEvent()

data class FundsValidationCompleted(
    override val correlationId: String,
    override val timestamp: Instant = Instant.now(),
    val hasValidFunds: Boolean,
    val errorMessage: String? = null
) : TransactionEvent()

data class LimitValidationRequested(
    override val correlationId: String,
    override val timestamp: Instant = Instant.now(),
    val fromAccount: Account,
    val requestedAmount: Long
) : TransactionEvent()

data class LimitValidationCompleted(
    override val correlationId: String,
    override val timestamp: Instant = Instant.now(),
    val isWithinLimit: Boolean,
    val errorMessage: String? = null
) : TransactionEvent()

data class TransactionProcessingRequested(
    override val correlationId: String,
    override val timestamp: Instant = Instant.now(),
    val fromAccount: Account,
    val toAccount: Account,
    val amount: Long
) : TransactionEvent()

data class AccountsUpdated(
    override val correlationId: String,
    override val timestamp: Instant = Instant.now(),
    val updatedFromAccount: Account,
    val updatedToAccount: Account
) : TransactionEvent()

data class TransactionRecordCreated(
    override val correlationId: String,
    override val timestamp: Instant = Instant.now(),
    val transaction: Transaction
) : TransactionEvent()

data class TransactionCompleted(
    override val correlationId: String,
    override val timestamp: Instant = Instant.now(),
    val transactionId: UUID,
    val fromAccountId: UUID,
    val toAccountId: UUID,
    val amount: Long
) : TransactionEvent()

data class TransactionFailed(
    override val correlationId: String,
    override val timestamp: Instant = Instant.now(),
    val fromAccountId: UUID,
    val toAccountId: UUID,
    val amount: Long,
    val reason: String
) : TransactionEvent()