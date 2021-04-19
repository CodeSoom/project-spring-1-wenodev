package com.weno.auth;

import com.weno.auth.dto.AuthResponseDto;
import com.weno.auth.dto.UserResultData;
import com.weno.auth.exception.AuthenticationBadRequestException;
import com.weno.user.User;
import com.weno.user.UserRepository;
import com.weno.user.dto.UserRequestDto;
import com.weno.user.dto.UserResponseDto;
import com.weno.utils.JwtUtil;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Transactional
@Service
public class AuthService {

    private final UserRepository userRepository;
    private final JwtUtil jwtUtil;

    public AuthService(UserRepository userRepository, JwtUtil jwtUtil) {
        this.userRepository = userRepository;
        this.jwtUtil = jwtUtil;
    }

    public UserResponseDto register(UserRequestDto userRequestDto) {
        User user = UserRequestDto.toEntity(userRequestDto);
        return UserResponseDto.of(userRepository.save(user));
    }

    public AuthResponseDto createToken(UserRequestDto userRequestDto) {
        UserResultData userResultData = authenticateUser(
                userRequestDto.getEmail(),
                userRequestDto.getPassword());

        String accessToken = jwtUtil.encode(userResultData.getEmail());

        return AuthResponseDto.of(accessToken);
    }

    public UserResultData authenticateUser(String email, String password) {
        return userRepository.findByEmail(email)
                .filter(user -> user.authenticate(password))
                .map(UserResultData::of)
                .orElseThrow(AuthenticationBadRequestException::new);
    }
}
