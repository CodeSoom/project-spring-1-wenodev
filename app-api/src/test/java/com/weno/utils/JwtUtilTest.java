package com.weno.utils;

import io.jsonwebtoken.security.Keys;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.nio.charset.StandardCharsets;
import java.security.Key;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class JwtUtilTest {

    private final String SECRET = "12345678901234567890123456789010";
    private final String TOKEN = "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ3ZW5vQGNvZGVzb29tLmNvbSJ9.weI7LH8ohPF56m8ngrGk_pXoviJdrPofupcfUVQoSHU";
    private final String EMAIL = "weno@codesoom.com";
    private JwtUtil jwtUtil;
    private Key key;

    @BeforeEach
    void setUp(){
        key = Keys.hmacShaKeyFor(SECRET.getBytes(StandardCharsets.UTF_8));
    }

    @Test
    void testEncode(){
        String token = JwtUtil.encode(EMAIL);
        assertThat(token).isEqualTo(TOKEN);
    }

}
