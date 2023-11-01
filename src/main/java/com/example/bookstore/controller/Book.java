package com.example.bookstore.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/bookstore/demo")
@RestController
public class Book {

    @GetMapping("/first")
    public ResponseEntity<String> say(){
        return ResponseEntity.ok("hello");
    }
}
