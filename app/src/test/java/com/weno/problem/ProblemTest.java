package com.weno.problem;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ProblemTest {

    private Problem problem;

    @BeforeEach
    void setUp(){
        problem = new Problem("dummy-test-title");
    }

    @Test
    void testTitle(){
        problem.getTest();
    }

}
