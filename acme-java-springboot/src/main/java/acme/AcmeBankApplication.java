package acme;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@OpenAPIDefinition(
    info = @Info(
        title = "Acme Bank API",
        version = "1.0",
        description = "Acme Bank API documentation"
    )
)
@SpringBootApplication
public class AcmeBankApplication {

    public static void main(String[] args) {
        SpringApplication.run(AcmeBankApplication.class, args);
    }
}
