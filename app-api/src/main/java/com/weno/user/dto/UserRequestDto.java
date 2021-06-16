package com.weno.user.dto;

import com.weno.user.User;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class UserRequestDto {
    private Long id;
    private String email;
    private String name;
    private String password;

    @Builder
    public UserRequestDto(Long id, String email, String name, String password) {
        this.id = id;
        this.email = email;
        this.name = name;
        this.password = password;
    }

    public static User toEntity(UserRequestDto userRequestDto) {
        return User.builder()
                .email(userRequestDto.getEmail())
                .name(userRequestDto.getName())
                .password(userRequestDto.getPassword())
                .build();
    }
}
