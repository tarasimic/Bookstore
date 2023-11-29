package com.example.bookstore.service.user;
import com.example.bookstore.config.JwtService;
import com.example.bookstore.model.user.LogIn;
import com.example.bookstore.model.user.Role;
import com.example.bookstore.model.user.User;
import com.example.bookstore.model.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    @Autowired
    private final UserRepository userRepository;
    private final PasswordEncoder encoder;
    private final JwtService jwtService;
    private final AuthenticationManager manager;

    public void register(User userReq){
        var user = User.builder().firstName(userReq.getFirstName()).lastName(userReq.getLastName()).email(userReq.getEmail()).password(encoder.encode(userReq.getPassword())).role(Role.USER).build();
        userRepository.save(user);
    }


    public String logIn(LogIn req){
        manager.authenticate(new UsernamePasswordAuthenticationToken(req.getEmail(), req.getPassword()));
        var user = userRepository.findByEmail(req.getEmail()).orElseThrow();
        return jwtService.generatesToken(user);
    }

}
