package com.weno;

import com.weno.utils.JwtUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

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
                .isEqualTo("eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiIxMjM0NTY3ODkwMTIzNDU2Nzg5MDEyMzQ1Njc4OTAxMCJ9.Ylg2FH4MNpTpNpVHMcwovATt4QARW3awwQu0bwHIcOg");
    }
}
