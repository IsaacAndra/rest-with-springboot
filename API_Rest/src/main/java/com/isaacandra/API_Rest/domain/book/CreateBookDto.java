package com.isaacandra.API_Rest.domain.book;

import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record CreateBookDto(
        String author,
        @NotNull
        LocalDateTime launchDate,
        @NotNull
        Float price,
        String title
) {
}
