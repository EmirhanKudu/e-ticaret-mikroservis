package com.ekudu.eticaretkullanici.controller;

import com.ekudu.eticaretkullanici.cache.TokenBlacklistService;
import com.ekudu.eticaretkullanici.dto.LoginRequestDto;
import com.ekudu.eticaretkullanici.dto.LoginResponseDto;
import com.ekudu.eticaretkullanici.dto.RegisterRequestDto;
import com.ekudu.eticaretkullanici.messaging.UserRegistrationProducer;
import com.ekudu.eticaretkullanici.model.UserEntity;
import com.ekudu.eticaretkullanici.security.JwtUtil;
import com.ekudu.eticaretkullanici.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.*;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.time.Duration;
import java.util.Optional;
import java.util.UUID;

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
    public LoginResponseDto login(@RequestBody LoginRequestDto loginRequestDto) {

        final Optional<UserEntity> userDetails = userService.getUserByUserName(loginRequestDto.getUsername());
        UserEntity userEntity = userDetails.orElseThrow(() -> new RuntimeException("Kullanıcı bulunamadı."));

        try {

            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            loginRequestDto.getUsername(),
                            loginRequestDto.getPassword()
                    )
            );
        } catch (BadCredentialsException e) {
            throw new RuntimeException("Kullanıcı adı veya şifre hatalı.");
        }
        final String token = jwtUtil.generateToken(userEntity);
        final String refreshToken = UUID.randomUUID().toString();
        LoginResponseDto loginResponseDto = new LoginResponseDto();
        loginResponseDto.setToken(token);
        loginResponseDto.setRefreshToken(refreshToken);

        return loginResponseDto;
    }

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody RegisterRequestDto req) {
        UserEntity saved = userService.registerUser(req, passwordEncoder);
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
