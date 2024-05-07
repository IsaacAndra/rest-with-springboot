package com.isaacandra.API_Rest.controller;

import com.isaacandra.API_Rest.domain.book.BookDto;
import com.isaacandra.API_Rest.domain.book.CreateBookDto;
import com.isaacandra.API_Rest.domain.book.EditBookDto;
import com.isaacandra.API_Rest.services.BookService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.awt.print.Book;
import java.util.List;

@RestController
@RequestMapping("/books")
public class BookController {

    @Autowired
    BookService bookService;


    @GetMapping
    public ResponseEntity<List<BookDto>> getAllBooks(){
        List<BookDto> books = bookService.findAllBooks();
        return ResponseEntity.ok(books);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BookDto> getBookById(@PathVariable Long id){
        BookDto book = bookService.findById(id);
        return ResponseEntity.ok(book);
    }

    @PostMapping
    @Transactional
    public ResponseEntity<BookDto> creatingBook(@RequestBody @Valid CreateBookDto data){
       BookDto newBook = bookService.createBook(data);
       return new ResponseEntity<>(newBook, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<BookDto> updatingBookById(@PathVariable Long id, @RequestBody @Valid EditBookDto data){
        BookDto updatedBook = bookService.updateBook(id, data);
        return ResponseEntity.ok(updatedBook);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletingBookById(@PathVariable Long id){
        bookService.deleteBook(id);
        return ResponseEntity.noContent().build();
    }

}
