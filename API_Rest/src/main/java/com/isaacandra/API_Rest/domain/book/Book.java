package com.isaacandra.API_Rest.domain.book;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity(name = "book")
@Table(name = "books")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "author")
    private String author;

    @Column(name = "launch_date")
    private LocalDateTime launchDate;

    @Column(name = "price")
    private Float price;

    @Column(name = "title")
    private String title;

    public Book(CreateBookDto data) {
        this.author = data.author();
        this.launchDate = data.launchDate();
        this.price = data.price();
        this.title = data.title();
    }

    public void updatedUser(EditBookDto data) {
        if (data.author() != null){
            this.author = data.author();
        }
        if (data.launchDate() != null){
            this.launchDate = data.launchDate();
        }
        if (data.price() != null){
            this.price = data.price();
        }
        if (data.title() != null){
            this.title = data.title();
        }
    }
}
