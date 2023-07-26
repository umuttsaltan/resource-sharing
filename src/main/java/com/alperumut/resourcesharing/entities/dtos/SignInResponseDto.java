package com.alperumut.resourcesharing.entities.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SignInResponseDto {
    private String name;
    private String username;
    private String email;
    private String token;
    private String profilePhoto;
}
