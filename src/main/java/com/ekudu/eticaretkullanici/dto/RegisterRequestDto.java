package com.ekudu.eticaretkullanici.dto;

import lombok.Data;
@Data
public class RegisterRequestDto {
    private String username;
    private Long phoneNumber;
    private String email;
    private String password;
}
