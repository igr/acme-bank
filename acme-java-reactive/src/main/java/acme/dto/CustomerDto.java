package acme.dto;

import java.util.UUID;

public record CustomerDto(
    UUID customerId,
    String name
) {}
