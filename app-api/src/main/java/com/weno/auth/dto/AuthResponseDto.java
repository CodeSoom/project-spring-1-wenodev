package com.weno.auth.dto;

import lombok.Builder;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Builder
public class AuthResponseDto {
    private String accessToken;

    @Builder
    public AuthResponseDto(String accessToken) {
        this.accessToken = accessToken;
    }

    public AuthResponseDto of(String accessToken){
        return AuthResponseDto.builder()
                .accessToken(accessToken)
                .build();
    }
}
