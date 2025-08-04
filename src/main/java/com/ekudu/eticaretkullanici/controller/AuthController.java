package com.ekudu.eticaretkullanici.controller;

import com.ekudu.eticaretkullanici.cache.TokenBlacklistService;
import com.ekudu.eticaretkullanici.dto.LoginRequest;
import com.ekudu.eticaretkullanici.dto.RegisterRequest;
import com.ekudu.eticaretkullanici.messaging.UserRegistrationProducer;
import com.ekudu.eticaretkullanici.model.UserEntity;
import com.ekudu.eticaretkullanici.security.JwtUtil;
import com.ekudu.eticaretkullanici.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.*;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.time.Duration;
import java.util.Optional;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserRegistrationProducer producer;

    @Autowired
    private TokenBlacklistService tokenBlacklistService;

    @Autowired
    private UserService userService;

    @Autowired
    private JwtUtil jwtUtil;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/login")
    public String login(@RequestBody LoginRequest loginRequest) {
        try {

            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            loginRequest.getUsername(),
                            loginRequest.getPassword()
                    )
            );
        } catch (BadCredentialsException e) {
            throw new RuntimeException("Kullanıcı adı veya şifre hatalı.");
        }


        final Optional<UserEntity> userDetails = userService.getUserByUserName(loginRequest.getUsername());
        UserEntity userEntity = userDetails.orElseThrow(() -> new RuntimeException("Kullanıcı bulunamadı."));


        final String token = jwtUtil.generateToken(userEntity);

        return token;
    }

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody RegisterRequest req) {
        UserEntity saved = userService.registerUser(req, passwordEncoder);
        // RabbitMQ publish:
        producer.publishNewUser(saved.getId(), saved.getUsername());
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body("Kullanıcı başarıyla oluşturuldu.");
    }
    @PostMapping("/logout")
    public ResponseEntity<?> logout(HttpServletRequest req) {
        String token = req.getHeader("Authorization").substring(7);
        tokenBlacklistService.blacklistToken(token, Duration.ofHours(1));
        return ResponseEntity.ok("Logged out");
    }



}
