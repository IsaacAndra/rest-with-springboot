package com.isaacandra.API_Rest.domain.book;

import java.time.LocalDateTime;
import java.util.Date;

public record BookDto(Long id, String author, Date launchDate, Double price, String title) {
}
