package com.ekudu.eticaretkullanici.service;
import com.ekudu.eticaretkullanici.dto.RegisterRequest;
import com.ekudu.eticaretkullanici.model.UserEntity;
import com.ekudu.eticaretkullanici.repository.UserRepository;
import com.ekudu.eticaretkullanici.service.UserService;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserEntity registerUser(RegisterRequest req, PasswordEncoder encoder) {
        userRepository.findByUsername(req.getUsername())
                .ifPresent(u -> { throw new RuntimeException("Bu kullanıcı adı zaten kayıtlı!"); });

        UserEntity user = new UserEntity();
        user.setUsername(req.getUsername());
        user.setEmail(req.getEmail());
        user.setPhoneNumber(req.getPhoneNumber());
        user.setRole("USER");
        user.setPassword(encoder.encode(req.getPassword()));

        return userRepository.save(user);   // artık entity dönüyor
    }


    public Optional<UserEntity> getUserById(long id) {
        return userRepository.findById(id);
    }
    public Optional<UserEntity> getUserByUserName(String userName) {
        return userRepository.findByUsername(userName);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Kullanıcı bulunamadı: " + username));

        return new org.springframework.security.core.userdetails.User(
                user.getUsername(),
                user.getPassword(),
                List.of(new SimpleGrantedAuthority(user.getRole()))
        );
    }
}


