package com.alperumut.resourcesharing.entities.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SignUpDto {
    private String name;
    private String username;
    private String email;
    private String password;
    private String profilePhoto;
}
