package com.isaacandra.API_Rest.services;

import com.isaacandra.API_Rest.domain.book.*;
import com.isaacandra.API_Rest.exceptions.BookNotFoundException;
import com.isaacandra.API_Rest.infra.ApplicationExceptionHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    ApplicationExceptionHandler exceptionHandler;

   public List<BookDto> findAllBooks(){
       List<Book> books = bookRepository.findAll();
       if (books.isEmpty() == true){
           throw new BookNotFoundException();
       }
       log.info("Finding all Books!");
       return books.stream().map(this::mapToBookDTO).collect(Collectors.toList());
   }

    public BookDto findById(Long id){
       Book book = bookRepository.findById(id).orElseThrow(() -> new BookNotFoundException("Book with Id " + id + " Was Not Found"));
        log.info("I found the book with Id "+ id);
       return mapToBookDTO(book);
    }

    public BookDto createBook(CreateBookDto data) {
        Book book = new Book(data);

        Book savedBook = bookRepository.save(book);
        return mapToBookDTO(savedBook);
    }

    public BookDto updateBook(Long id, EditBookDto data) {
       Book book = bookRepository.findById(id).orElseThrow(() -> new BookNotFoundException("Book with Id " + id + " Was Not Found"));
       book.updatedUser(data);
       bookRepository.save(book);
        log.info("Updating Book by Id " + id);
       return mapToBookDTO(book);
    }

    public void deleteBook(Long id){
       Book book = bookRepository.findById(id).orElseThrow(() -> new BookNotFoundException("Book with Id " + id + " Was Not Found"));
       bookRepository.delete(book);
        log.info("Deleting book by ID to endpoint /books");
    }





    private BookDto mapToBookDTO(Book book) {
        return new BookDto(book.getId(), book.getAuthor(), book.getLaunchDate(), book.getPrice(), book.getTitle());
    }
}
