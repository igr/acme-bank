package acme

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class AcmeBankApplication

fun main(args: Array<String>) {
	runApplication<AcmeBankApplication>(*args)
}
