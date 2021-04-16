package com.weno.auth;

import com.weno.user.User;
import com.weno.user.UserRepository;
import com.weno.user.dto.UserRequestDto;
import com.weno.user.dto.UserResponseDto;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Transactional
@Service
public class AuthService {

    private final UserRepository userRepository;

    public AuthService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserResponseDto create(UserRequestDto userRequestDto) {
        User user = UserRequestDto.toEntity(userRequestDto);
        return UserResponseDto.of(userRepository.save(user));
    }
}
