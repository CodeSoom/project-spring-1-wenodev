package com.weno;

import com.weno.utils.JwtUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class AppTest {

    @Autowired
    private JwtUtil jwtUtil;

    @Test
    void basicTest(){
        System.out.println("working..");
    }

    @Test
    void testJwt(){
        System.out.println(jwtUtil.encode("X05SIgPu8QT9FeTq7MBS0ty5M5JIZ8Bp"));
    }
}
