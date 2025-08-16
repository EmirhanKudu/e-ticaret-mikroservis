package com.ekudu.eticaretkullanici.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.userdetails.User;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "siparis")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name  = "user_id")
    private UserEntity user;


    @OneToMany(mappedBy = "orderEntity", cascade = CascadeType.ALL, orphanRemoval = true)
    // burda order birden fazla şey tutabilir , ilişkinin sahibi bu sınıf değil diyorum order a yapılan bütün işlemler orderıteme de yapılır.
    private List<OrderItemEntity> items =new ArrayList<>();







}
