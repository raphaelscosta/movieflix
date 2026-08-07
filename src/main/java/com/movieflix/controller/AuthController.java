
package com.movieflix.controller;

import com.movieflix.config.TokenService;
import com.movieflix.dto.request.LoginRequest;
import com.movieflix.dto.request.UserRequestDTO;
import com.movieflix.dto.response.LoginResponse;
import com.movieflix.dto.response.UserResponseDTO;
import com.movieflix.entity.User;
import com.movieflix.exception.UsernameOrPasswordInvalidException;
import com.movieflix.mapper.UserMapper;
import com.movieflix.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/movieflix/auth")
public class AuthController {

    private final UserService userService;
    private final AuthenticationManager authenticationManager;
    private final TokenService tokenService;

    public AuthController(UserService userService, AuthenticationManager authenticationManager, TokenService tokenService){
        this.userService = userService;
        this.authenticationManager = authenticationManager;
        this.tokenService = tokenService;
    }

    @PostMapping("/register")
    public ResponseEntity<UserResponseDTO> register(@Valid @RequestBody UserRequestDTO userRequestDTO){
        User user = userService.register(UserMapper.toEntity(userRequestDTO));
        return ResponseEntity.status(HttpStatus.CREATED).body(UserMapper.toResponse(user));
    }
    
    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@Valid @RequestBody LoginRequest loginRequest){
        try {
            UsernamePasswordAuthenticationToken userAndPass = new UsernamePasswordAuthenticationToken(
                    loginRequest.email(), loginRequest.password());
            Authentication authenticate = authenticationManager.authenticate(userAndPass);

            User user = (User) authenticate.getPrincipal();
            String token = tokenService.generateToken(user);
            return ResponseEntity.ok(new LoginResponse(token));
        }
        catch(BadCredentialsException e){
            throw new UsernameOrPasswordInvalidException("Usuário ou senha inválida");
        }
    }
}
