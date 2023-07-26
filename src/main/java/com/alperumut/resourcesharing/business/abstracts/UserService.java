package com.alperumut.resourcesharing.business.abstracts;

import com.alperumut.resourcesharing.entities.concretes.User;
import com.alperumut.resourcesharing.entities.dtos.SignInDto;
import com.alperumut.resourcesharing.entities.dtos.SignInResponseDto;
import com.alperumut.resourcesharing.entities.dtos.SignUpDto;

public interface UserService {
    SignInResponseDto signIn(SignInDto signInDto);
    User signUp(SignUpDto signUpDto);
}
