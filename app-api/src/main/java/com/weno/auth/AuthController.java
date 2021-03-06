package com.weno.auth;

import com.weno.auth.dto.AuthResponseDto;
import com.weno.user.dto.UserRequestDto;
import com.weno.user.dto.UserResponseDto;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {
    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/login")
    public AuthResponseDto login(@RequestBody UserRequestDto userRequestDto) {
        return authService.login(userRequestDto);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/register")
    public UserResponseDto register(@RequestBody UserRequestDto userRequestDto){
        return authService.register(userRequestDto);
    }
}
