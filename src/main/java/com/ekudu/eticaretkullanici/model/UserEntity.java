package com.ekudu.eticaretkullanici.model;
import jakarta.persistence.*;
import lombok.Data;
@Entity
@Table(name = "kullanici")
@Data

public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    private Long phoneNumber;
    private String email;
    private String password;
    private String role;
    //re

}
