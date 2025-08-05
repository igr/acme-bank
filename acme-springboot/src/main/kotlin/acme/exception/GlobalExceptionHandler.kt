package acme.exception

import org.slf4j.LoggerFactory
import org.springframework.dao.OptimisticLockingFailureException
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
class GlobalExceptionHandler {

    @ExceptionHandler(IllegalArgumentException::class)
    fun handleIllegalArgumentException(e: IllegalArgumentException): ResponseEntity<Map<String, String>> {
        return ResponseEntity.badRequest().body(mapOf("error" to (e.message ?: "Invalid request")))
    }

    @ExceptionHandler(OptimisticLockingFailureException::class)
    fun handleOptimisticLockingFailureException(e: OptimisticLockingFailureException): ResponseEntity<Map<String, String>> {
        return ResponseEntity.status(HttpStatus.CONFLICT)
            .body(mapOf("error" to "Resource was modified by another transaction. Please retry."))
    }

    @ExceptionHandler(Exception::class)
    fun handleGenericException(e: Exception): ResponseEntity<Map<String, String>> {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
            .body(mapOf("error" to "An unexpected error occurred"))
    }
}
