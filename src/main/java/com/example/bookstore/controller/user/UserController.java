package com.example.bookstore.controller.user;

import com.example.bookstore.model.LogIn;
import com.example.bookstore.model.User;
import com.example.bookstore.service.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/bookstore")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody User user){
        userService.register(user);
        return ResponseEntity.ok("successful registration");
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LogIn logIn){
        return ResponseEntity.ok(userService.logIn(logIn));
    }
}
