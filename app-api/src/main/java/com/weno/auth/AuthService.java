package com.weno.auth;

import com.weno.auth.dto.AuthResponseDto;
import com.weno.auth.dto.UserResultData;
import com.weno.auth.exception.AuthenticationBadRequestException;
import com.weno.auth.exception.UserEmailDuplicatedException;
import com.weno.role.Role;
import com.weno.role.RoleRepository;
import com.weno.user.User;
import com.weno.user.UserRepository;
import com.weno.user.dto.UserRequestDto;
import com.weno.user.dto.UserResponseDto;
import com.weno.user.errors.UserNotFoundException;
import com.weno.utils.JwtUtil;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
@Service
public class AuthService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final JwtUtil jwtUtil;

    public AuthService(UserRepository userRepository, RoleRepository roleRepository, JwtUtil jwtUtil) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.jwtUtil = jwtUtil;
    }

    public UserResponseDto register(UserRequestDto userRequestDto) {
        String email = userRequestDto.getEmail();
        if (userRepository.existsByEmail(email)){
            throw new UserEmailDuplicatedException(email);
        }
        
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

    public String parseToken(String accessToken) {
        return null;
    }

    public List<Role> roles(String email) {
        User user = userRepository.findByEmail(email).orElseThrow(()-> new UserNotFoundException("no user email : " + email));
        return roleRepository.findAllByUser(user);
    }
}
