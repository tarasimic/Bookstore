package com.example.bookstore.controller.order;

import com.example.bookstore.model.book.Book;
import com.example.bookstore.service.order.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/bookstore")
@RequiredArgsConstructor
public class OrderController {

    @Autowired
    private final OrderService orderService;

    @PostMapping("/order")
    public ResponseEntity<String> orderBook(@RequestBody Book book_id){
        orderService.orderBook(book_id);
        return ResponseEntity.ok("You ordered a book");
    }
}
