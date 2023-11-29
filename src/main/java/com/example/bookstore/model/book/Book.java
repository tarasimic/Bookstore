package com.example.bookstore.model.book;

import com.example.bookstore.model.order.Order;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Data
@Entity
@Table
@NoArgsConstructor
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int book_id;
    private String name;
    private String author;
    private int price;
    @OneToMany(mappedBy = "book", cascade = CascadeType.ALL)
    private List<Order> orders;


    public Book(String name, String author, int price) {
        this.name = name;
        this.author = author;
        this.price = price;
    }
}
