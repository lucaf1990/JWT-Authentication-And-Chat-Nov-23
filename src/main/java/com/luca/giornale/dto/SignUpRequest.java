package com.luca.giornale.dto;

import lombok.Data;

@Data
public class SignUpRequest {

private String name;
private String photoUrl;
    private String email;
    private String password;
}
