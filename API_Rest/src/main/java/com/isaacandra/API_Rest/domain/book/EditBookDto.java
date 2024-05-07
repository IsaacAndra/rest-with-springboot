package com.isaacandra.API_Rest.domain.book;

import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;
import java.util.Date;

public record EditBookDto(
        String author,
        Date launchDate,
        Double price,
        String title
) {
}
