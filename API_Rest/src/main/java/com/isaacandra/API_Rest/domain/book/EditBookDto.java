package com.isaacandra.API_Rest.domain.book;

import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record EditBookDto(
        String author,
        LocalDateTime launchDate,
        Float price,
        String title
) {
}
