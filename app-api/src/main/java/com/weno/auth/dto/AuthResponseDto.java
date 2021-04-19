package com.weno.auth.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;


@NoArgsConstructor
@Getter
public class AuthResponseDto {
    private String accessToken;

    @Builder
    public AuthResponseDto(String accessToken) {
        this.accessToken = accessToken;
    }

    public static AuthResponseDto of(String accessToken){
        return AuthResponseDto.builder()
                .accessToken(accessToken)
                .build();
    }
}
