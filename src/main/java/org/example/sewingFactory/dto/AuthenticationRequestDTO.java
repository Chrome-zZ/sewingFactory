package org.example.sewingFactory.dto;


import lombok.Data;

@Data
public class AuthenticationRequestDTO {
    private String login;
    private String password;
}
