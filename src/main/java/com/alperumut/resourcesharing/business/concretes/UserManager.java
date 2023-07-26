package com.alperumut.resourcesharing.business.concretes;

import com.alperumut.resourcesharing.business.abstracts.UserService;
import com.alperumut.resourcesharing.entities.concretes.Rank;
import com.alperumut.resourcesharing.entities.concretes.User;
import com.alperumut.resourcesharing.entities.dtos.SignInDto;
import com.alperumut.resourcesharing.entities.dtos.SignInResponseDto;
import com.alperumut.resourcesharing.entities.dtos.SignUpDto;
import com.alperumut.resourcesharing.exceptions.ResourceSharingAPIException;
import com.alperumut.resourcesharing.repositories.RankRepository;
import com.alperumut.resourcesharing.repositories.UserRankRepository;
import com.alperumut.resourcesharing.repositories.UserRepository;
import com.alperumut.resourcesharing.security.jwt.JwtTokenProvider;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;
import java.util.logging.Logger;

@Service
public class UserManager implements UserService {

    private final UserRepository userRepository;
    private final AuthenticationManager authenticationManager;
    private final RankRepository rankRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider tokenProvider;

    private final UserRankRepository userRankRepository;

    public UserManager(UserRepository userRepository, AuthenticationManager authenticationManager, RankRepository rankRepository, PasswordEncoder passwordEncoder, JwtTokenProvider tokenProvider, UserRankRepository userRankRepository) {
        this.userRepository = userRepository;
        this.authenticationManager = authenticationManager;
        this.rankRepository = rankRepository;
        this.passwordEncoder = passwordEncoder;
        this.tokenProvider = tokenProvider;
        this.userRankRepository = userRankRepository;
    }

    @Override
    public SignInResponseDto signIn(SignInDto signInDto) {
        // Log the credentials before authentication
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(signInDto.getUsernameOrEmail(), signInDto.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String token = tokenProvider.generateToken(authentication);

        User user = userRepository.findByUsernameOrEmail(signInDto.getUsernameOrEmail(),signInDto.getUsernameOrEmail())
                .orElseThrow(() -> new ResourceSharingAPIException(HttpStatus.NOT_FOUND, "User not found!"));
        SignInResponseDto response = new SignInResponseDto();
        response.setEmail(user.getEmail());
        response.setUsername(user.getUsername());
        response.setName(user.getName());
        response.setProfilePhoto(user.getProfilePhoto());
        response.setToken(token);


        return response;
    }

    @Override
    public User signUp(SignUpDto signUpDto) {
        //Is username available ?
        if(userRepository.existsByUsername(signUpDto.getUsername())){
            throw new ResourceSharingAPIException(HttpStatus.BAD_REQUEST,"Username is already taken!");
        }

        //Is email available?
        if(userRepository.existsByEmail(signUpDto.getEmail())){
            throw new ResourceSharingAPIException(HttpStatus.BAD_REQUEST,"Email is already taken!");
        }

        User user = new User();
        user.setName(signUpDto.getName());
        user.setUsername(signUpDto.getUsername());
        user.setEmail(signUpDto.getEmail());
        user.setProfilePhoto(signUpDto.getProfilePhoto());
        user.setPassword(passwordEncoder.encode(signUpDto.getPassword()));
        Set<Rank> rank = new HashSet<>();
        Rank userRank = rankRepository.findById(1L)
                .orElseThrow(() -> new ResourceSharingAPIException(HttpStatus.NOT_FOUND, "User rank with ID 1 not found"));
        rank.add(userRank);
        user.setRank(rank);

        userRepository.save(user);
        return user;
    }

}
