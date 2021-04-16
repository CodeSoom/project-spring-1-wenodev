package com.weno.auth;

import com.weno.user.User;
import com.weno.user.UserRepository;
import com.weno.user.dto.UserRequestDto;
import com.weno.user.dto.UserResponseDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

class AuthServiceTest {

    private UserRepository userRepository;
    private AuthService authService;
    private User newUser;
    private UserRequestDto userRequestDto;
    private final static Long EXISTED_ID = 1L;
    private final static Long CREATED_ID = 2L;
    private final static String CREATE_EMAIL = "test-email-created";
    private final static String CREATE_NAME = "test-name-created";
    private final static String CREATE_PASSWORD = "test-password-created";

    @BeforeEach
    void setUp() {
        userRepository = mock(UserRepository.class);
        authService = new AuthService(userRepository);

        newUser = User.builder()
                .id(CREATED_ID)
                .email(CREATE_EMAIL)
                .name(CREATE_NAME)
                .password(CREATE_PASSWORD)
                .build();

        userRequestDto = UserRequestDto.builder()
                .email(CREATE_EMAIL)
                .name(CREATE_EMAIL)
                .password(CREATE_PASSWORD)
                .build();

    }

    @Test
    void testCreate(){
        given(userRepository.save(any(User.class))).willReturn(newUser);
        UserResponseDto userResponseDto = authService.create(userRequestDto);
        assertThat(userResponseDto.getId()).isEqualTo(CREATED_ID);
    }

}
