package com.weno.utils;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

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
        jwtUtil = new JwtUtil(SECRET);
    }

    @Test
    void testEncode(){
        String token = jwtUtil.encode(EMAIL);
        assertThat(token).isEqualTo(TOKEN);
    }

    @Test
    void testDecode(){
        assertThat(jwtUtil.decode(TOKEN).getSubject()).isEqualTo(EMAIL);
    }

}
