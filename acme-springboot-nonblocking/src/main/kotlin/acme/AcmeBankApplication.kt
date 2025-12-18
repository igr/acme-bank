package acme

import io.swagger.v3.oas.annotations.OpenAPIDefinition
import io.swagger.v3.oas.annotations.info.Info
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@OpenAPIDefinition(
    info = Info(
        title = "Acme Bank API (Non-Blocking)",
        version = "1.0",
        description = "Acme Bank API documentation - Non-blocking version with R2DBC"
    )
)
@SpringBootApplication
class AcmeBankApplication

fun main(args: Array<String>) {
    runApplication<AcmeBankApplication>(*args)
}
