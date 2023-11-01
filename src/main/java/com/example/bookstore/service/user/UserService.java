package com.example.bookstore.service.user;
//
//import com.example.bookstore.auth.AuthenticationRequest;
//import com.example.bookstore.auth.AuthenticationResponse;
//import com.example.bookstore.auth.RegisterRequest;
import com.example.bookstore.config.JwtService;
import com.example.bookstore.model.LogIn;
import com.example.bookstore.model.Role;
import com.example.bookstore.model.User;
import com.example.bookstore.model.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.security.NoSuchAlgorithmException;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder encoder;
    private final JwtService jwtService;
    private final AuthenticationManager manager;

    public void register(User userReq){
        var user = User.builder().firstName(userReq.getFirstName()).lastName(userReq.getLastName()).email(userReq.getEmail()).password(encoder.encode(userReq.getPassword())).role(Role.USER).build();
        userRepository.save(user);
    }

//    public AuthenticationResponse authenticate(AuthenticationRequest request) throws NoSuchAlgorithmException {
//        manager.authenticate(new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));
//        var user = userRepository.findByEmail(request.getEmail()).orElseThrow();
//        var jwt = jwtService.generatesToken(user);
//        return AuthenticationResponse.builder().token(jwt).build();
//    }

    public String logIn(LogIn req){
        manager.authenticate(new UsernamePasswordAuthenticationToken(req.getEmail(), req.getPassword()));
        var user = userRepository.findByEmail(req.getEmail()).orElseThrow();
        return jwtService.generatesToken(user);
    }

}
