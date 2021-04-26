package com.weno.auth;

import com.weno.auth.dto.UserResultData;
import com.weno.role.RoleRepository;
import com.weno.user.User;
import com.weno.user.UserRepository;
import com.weno.user.dto.UserRequestDto;
import com.weno.user.dto.UserResponseDto;
import com.weno.utils.JwtUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

class AuthServiceTest {

    private RoleRepository roleRepository;
    private UserRepository userRepository;
    private JwtUtil jwtUtil;
    private PasswordEncoder passwordEncoder;
    private AuthService authService;
    private UserRequestDto userRequestDto;

    private User existedUser;
    private final static Long EXISTED_ID = 1L;
    private final static String EXISTED_EMAIL = "test-email-existed";
    private final static String EXISTED_NAME = "test-name-existed";
    private final static String EXISTED_PASSWORD = "test-password-existed";

    private User newUser;
    private final static Long CREATED_ID = 2L;
    private final static String CREATE_EMAIL = "test-email-created";
    private final static String CREATE_NAME = "test-name-created";
    private final static String CREATE_PASSWORD = "test-password-created";

    private final String TOKEN = "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ3ZW5vQGNvZGVzb29tLmNvbSJ9.weI7LH8ohPF56m8ngrGk_pXoviJdrPofupcfUVQoSHU";

    @BeforeEach
    void setUp() {
        userRepository = mock(UserRepository.class);
        roleRepository = mock(RoleRepository.class);
        passwordEncoder = mock(PasswordEncoder.class);
        jwtUtil = mock(JwtUtil.class);

        authService = new AuthService(userRepository, roleRepository, jwtUtil, passwordEncoder);

        userRequestDto = UserRequestDto.builder()
                .email(CREATE_EMAIL)
                .name(CREATE_EMAIL)
                .password(CREATE_PASSWORD)
                .build();

        existedUser = User.builder()
                .id(EXISTED_ID)
                .email(EXISTED_EMAIL)
                .name(EXISTED_NAME)
                .password(EXISTED_PASSWORD)
                .build();

        newUser = User.builder()
                .id(CREATED_ID)
                .email(CREATE_EMAIL)
                .name(CREATE_NAME)
                .password(CREATE_PASSWORD)
                .build();

        UserResultData userResultData = UserResultData.builder()
                .id(EXISTED_ID)
                .email(EXISTED_EMAIL)
                .name(EXISTED_NAME)
                .password(EXISTED_PASSWORD)
                .build();

    }

    @Test
    void testCreate(){
        given(userRepository.save(any(User.class))).willReturn(newUser);
        UserResponseDto userResponseDto = authService.register(userRequestDto);
        assertThat(userResponseDto.getId()).isEqualTo(CREATED_ID);
    }

    @Test
    void testAuthenticateUser() {
        given(userRepository.findByEmail(EXISTED_EMAIL)).willReturn(Optional.of(existedUser));
        UserResultData userResultData = authService.authenticateUser(EXISTED_EMAIL, EXISTED_PASSWORD);
        assertThat(userResultData.getEmail()).isEqualTo(EXISTED_EMAIL);
    }

    @Test
    void testParseToken(){
        jwtUtil.decode(TOKEN);
    }

}
