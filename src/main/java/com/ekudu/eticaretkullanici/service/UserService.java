package com.ekudu.eticaretkullanici.service;
import com.ekudu.eticaretkullanici.dto.RegisterRequestDto;
import com.ekudu.eticaretkullanici.dto.UserResponseDto;
import com.ekudu.eticaretkullanici.model.UserEntity;
import com.ekudu.eticaretkullanici.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserEntity registerUser(RegisterRequestDto req, PasswordEncoder encoder) {
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


    public UserResponseDto getUserById(Long id) {
    Optional <UserEntity> user = userRepository.findById(id);
        UserResponseDto userResponseDto = new UserResponseDto();
    if (user.isPresent()) {
        UserEntity userEntity = user.get();
        userResponseDto.setId(userEntity.getId());
        userResponseDto.setUsername(userEntity.getUsername());
        userResponseDto.setEmail(userEntity.getEmail());
        userResponseDto.setPhoneNumber(userEntity.getPhoneNumber());
    }
    else  {
        throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Kullanıcı bulunamadı");
    }
    return userResponseDto;
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


