package acme

import kotlin.random.Random

data class AcmeConfiguration(
    val totalCustomers: Int = 100,
    val accountsPerCustomer: () -> Int = { Random.nextInt(1, 3) },
    val randomAmountPerAccount: () -> Long = { Random.nextLong(1_000, 1_000_000) },
    val randomWithdrawalLimit: () -> Long = {
        if (Random.nextInt(100) < 30) {
            // 30% of the time, the withdrawal limit is unlimited
            -1L
        } else {
            Random.nextLong(100_000, 1_000_000)
        }
    }
)

data class LoadConfiguration(
    val totalRequests: Int = 10_000,
)