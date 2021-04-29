package com.weno;

import com.weno.utils.JwtUtil;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.security.Key;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class AppTest {

    @Autowired
    private JwtUtil jwtUtil;

    @Test
    void basicTest(){
        System.out.println("working..");
    }

    @Test
    void testJwtEncode(){
        assertThat((jwtUtil.encode("12345678901234567890123456789010")))
                .isEqualTo("eyJhbGciOiJIUzI1NiJ9.eyJlbWFpbCI6IjEyMzQ1Njc4OTAxMjM0NTY3ODkwMTIzNDU2Nzg5MDEwIn0.MuabToDnERkEmd4OAPHlCoxM7eWB-Jr6rrqRNeV_S4A");

    }
}
