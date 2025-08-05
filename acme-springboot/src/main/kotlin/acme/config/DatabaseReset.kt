package acme.config

import jakarta.annotation.PostConstruct
import org.flywaydb.core.Flyway
import org.springframework.stereotype.Component

@Component
class DatabaseReset(private val flyway: Flyway) {
    fun resetDatabase() {
        flyway.clean()
        flyway.migrate()
    }

    @PostConstruct
    fun init() {
        resetDatabase()
    }

}