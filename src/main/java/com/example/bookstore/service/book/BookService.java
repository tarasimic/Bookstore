package com.example.bookstore.service.book;

import com.example.bookstore.model.book.Book;
import com.example.bookstore.model.book.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BookService {

    private final BookRepository bookRepo;

    public List<Book> showBooks(){
        return bookRepo.findAll();
    }
}
