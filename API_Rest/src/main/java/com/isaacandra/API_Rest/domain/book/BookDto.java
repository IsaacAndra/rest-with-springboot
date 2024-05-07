package com.isaacandra.API_Rest.domain.book;

import java.time.LocalDateTime;

public record BookDto(String author, LocalDateTime launchDate, float price, String title) {
}
