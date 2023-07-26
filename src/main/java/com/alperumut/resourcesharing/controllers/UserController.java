package com.alperumut.resourcesharing.controllers;

import com.alperumut.resourcesharing.business.concretes.UserManager;
import com.alperumut.resourcesharing.entities.concretes.User;
import com.alperumut.resourcesharing.entities.dtos.SignInDto;
import com.alperumut.resourcesharing.entities.dtos.SignInResponseDto;
import com.alperumut.resourcesharing.entities.dtos.SignUpDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private UserManager userManager;

    public UserController(UserManager userManager) {
        this.userManager = userManager;
    }

    @PostMapping(value = {"/login","/signIn"})
    public ResponseEntity<SignInResponseDto> login(@RequestBody SignInDto signInDto){
        return ResponseEntity.ok(userManager.signIn(signInDto));
    }

    @PostMapping(value = {"/register", "signup"})
    public ResponseEntity<User> register(@RequestBody SignUpDto signUpDto){
        User user = userManager.signUp(signUpDto);
        return new ResponseEntity<>(user, HttpStatus.CREATED);
    }


}
