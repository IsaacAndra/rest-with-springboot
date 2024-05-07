package com.isaacandra.API_Rest.domain.book;

import java.time.LocalDateTime;

public record BookDto(Long id, String author, LocalDateTime launchDate, Float price, String title) {
}
