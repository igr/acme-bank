package acme.exception;

import org.springframework.dao.OptimisticLockingFailureException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import reactor.core.publisher.Mono;

import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(IllegalArgumentException.class)
    public Mono<ResponseEntity<Map<String, String>>> handleIllegalArgumentException(IllegalArgumentException e) {
        String message = e.getMessage() != null ? e.getMessage() : "Invalid request";
        return Mono.just(ResponseEntity.badRequest().body(Map.of("error", message)));
    }

    @ExceptionHandler(OptimisticLockingFailureException.class)
    public Mono<ResponseEntity<Map<String, String>>> handleOptimisticLockingFailureException(OptimisticLockingFailureException e) {
        return Mono.just(ResponseEntity.status(HttpStatus.CONFLICT)
            .body(Map.of("error", "Resource was modified by another transaction. Please retry.")));
    }

    @ExceptionHandler(Exception.class)
    public Mono<ResponseEntity<Map<String, String>>> handleGenericException(Exception e) {
        return Mono.just(ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
            .body(Map.of("error", "An unexpected error occurred")));
    }
}
