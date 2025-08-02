package com.ekudu.eticaretkullanici.dto;

import lombok.Data;
@Data
public class RegisterRequest {
    private String username;
    private Long phoneNumber;
    private String email;
    private String password;
}
