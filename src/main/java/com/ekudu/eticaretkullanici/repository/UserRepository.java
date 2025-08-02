package com.ekudu.eticaretkullanici.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import com.ekudu.eticaretkullanici.model.UserEntity;

import java.util.Optional;
public interface UserRepository extends JpaRepository<UserEntity, Long> {
    Optional<UserEntity> findByUsername(String username);

}
