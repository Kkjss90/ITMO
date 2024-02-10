package com.example.models;

import lombok.Data;

@Data
public class RegisterReq {
    private String username;
    private String password;
    private String password2;
}
