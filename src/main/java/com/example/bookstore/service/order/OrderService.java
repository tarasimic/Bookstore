package com.example.bookstore.service.order;

import com.example.bookstore.model.book.Book;
import com.example.bookstore.model.book.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OrderService {

    @Autowired
    private final BookRepository bookRepository;
    private final List<Optional<Book>> books;

    public void orderBook(Book book_id){
        var book = bookRepository.findById(book_id.getBook_id());
        books.add(book);
    }
}
