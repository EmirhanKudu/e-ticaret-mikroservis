package com.ekudu.eticaretkullanici.dto;

import lombok.Data;

@Data
public class UserResponseDto {
    private Long id;
    private String username;
    private String email;
    private Long phoneNumber;
    private String role;

}
