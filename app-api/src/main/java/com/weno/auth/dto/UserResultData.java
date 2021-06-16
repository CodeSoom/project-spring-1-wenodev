package com.weno.auth.dto;

import com.weno.user.User;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class UserResultData {
    private Long id;
    private String email;
    private String name;
    private String password;

    @Builder
    public UserResultData(Long id, String email, String name, String password) {
        this.id = id;
        this.email = email;
        this.name = name;
        this.password = password;
    }

    public static UserResultData of(User user){
        return UserResultData.builder()
                .id(user.getId())
                .email(user.getEmail())
                .name(user.getName())
                .password(user.getPassword())
                .build();
    }

}
