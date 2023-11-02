package com.example.bookstore.controller.book;

import com.example.bookstore.model.book.Book;
import com.example.bookstore.service.book.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping("/bookstore/books")
@RestController
@RequiredArgsConstructor
public class BookController {

    @Autowired
    private final BookService bookService;

    @GetMapping("/allBooks")
    public ResponseEntity<List<Book>> showBooks(){
        return ResponseEntity.ok(bookService.showBooks());
    }
}
