package com.isaacandra.API_Rest.domain.book;

import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;
import java.util.Date;

public record CreateBookDto(
        String author,
        @NotNull
        Date launchDate,
        @NotNull
        Double price,
        String title
) {
}
