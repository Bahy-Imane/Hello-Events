package com.devart.helloevents.dto;

import com.devart.helloevents.model.RoleEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SignUpDto {

    private String userName;
    private String email;
    private String password;
    private RoleEnum role;  // Added role field

}

