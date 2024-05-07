package com.isaacandra.API_Rest.controller;

import com.isaacandra.API_Rest.domain.book.BookDto;
import com.isaacandra.API_Rest.domain.book.CreateBookDto;
import com.isaacandra.API_Rest.domain.book.EditBookDto;
import com.isaacandra.API_Rest.services.BookService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
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
@Tag(name = "Books", description = "Endpoints for Manager Books")
public class BookController {

    @Autowired
    BookService bookService;


    @GetMapping
    @Operation(summary = "Find all Books", description = "Find all Books",
            tags = {"Books"},
            responses = {
                    @ApiResponse(description = "Success", responseCode = "200"),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal Server Error", responseCode = "500", content = @Content)
            })public ResponseEntity<List<BookDto>> getAllBooks(){
        List<BookDto> books = bookService.findAllBooks();
        return ResponseEntity.ok(books);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Find a Book", description = "Find a Book",
            tags = {"Books"},
            responses = {
                    @ApiResponse(description = "Success", responseCode = "200"),
                    @ApiResponse(description = "No Content", responseCode = "204", content = @Content),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal Server Error", responseCode = "500", content = @Content)
            })
    public ResponseEntity<BookDto> getBookById(@PathVariable Long id){
        BookDto book = bookService.findById(id);
        return ResponseEntity.ok(book);
    }

    @PostMapping
    @Transactional
    @Operation(summary = "Add a new Book", description = "Add a new Book",
            tags = {"Books"},
            responses = {
                    @ApiResponse(description = "Success", responseCode = "200"),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Internal Server Error", responseCode = "500", content = @Content)
            })
    public ResponseEntity<BookDto> creatingBook(@RequestBody @Valid CreateBookDto data){
       BookDto newBook = bookService.createBook(data);
       return new ResponseEntity<>(newBook, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update a Book", description = "Update a Book",
            tags = {"Books"},
            responses = {
                    @ApiResponse(description = "Updated", responseCode = "200"),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal Server Error", responseCode = "500", content = @Content)
            })
    public ResponseEntity<BookDto> updatingBookById(@PathVariable Long id, @RequestBody @Valid EditBookDto data){
        BookDto updatedBook = bookService.updateBook(id, data);
        return ResponseEntity.ok(updatedBook);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete a Book", description = "Delete a Book",
            tags = {"Books"},
            responses = {
                    @ApiResponse(description = "No Content", responseCode = "204"),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal Server Error", responseCode = "500", content = @Content)
            })
    public ResponseEntity<Void> deletingBookById(@PathVariable Long id){
        bookService.deleteBook(id);
        return ResponseEntity.noContent().build();
    }

}
