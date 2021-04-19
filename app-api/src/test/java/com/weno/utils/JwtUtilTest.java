package com.weno.utils;

import io.jsonwebtoken.security.Keys;
import org.junit.jupiter.api.Test;

import java.nio.charset.StandardCharsets;

class JwtUtilTest {

    private final String SECRET = "12345678901234567890123456789010";

    @Test
    void testGetKey(){
        System.out.println(Keys.hmacShaKeyFor(SECRET.getBytes(StandardCharsets.UTF_8)));
    }

}
