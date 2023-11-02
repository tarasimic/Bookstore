package com.example.bookstore.db;

import com.example.bookstore.model.book.Book;
import com.example.bookstore.model.book.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
public class DBOperationRunner implements CommandLineRunner {

    @Autowired
    private BookRepository bookRepository;

    @Override
    public void run(String... args) throws Exception {
        bookRepository.saveAll(Arrays.asList(new Book("Powerless", "Lauren Roberts", 10), new Book("Holly", "Stephen King", 30), new Book("Bright young woman", "Jessica Knole", 20), new Book("Things we left behind", "Lucy Score", 15), new Book("Beach rivals", "Georgie Tilney", 13)));
    }
}
