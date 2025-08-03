package com.ekudu.eticaretkullanici.controller;

import com.ekudu.eticaretkullanici.dto.LoginRequest;
import com.ekudu.eticaretkullanici.dto.RegisterRequest;
import com.ekudu.eticaretkullanici.model.UserEntity;
import com.ekudu.eticaretkullanici.security.JwtUtil;
import com.ekudu.eticaretkullanici.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.*;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

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
        UserEntity userEntity = userDetails.orElseThrow(() -> new RuntimeException("Kullanici bulunamadi."));


        final String token = jwtUtil.generateToken(userEntity);

        return token;
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegisterRequest registerRequest) {
        String result = userService.registerUser(registerRequest, passwordEncoder);
        return ResponseEntity.ok(result);
    }

}
