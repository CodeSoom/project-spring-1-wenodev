package com.weno.problem;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class ProblemTest {

    private Problem problem;

    @BeforeEach
    void setUp(){
        problem = new Problem("dummy-test-title");
    }

    @Test
    void testTitle(){
        assertThat(problem.getTitle()).isEqualTo("dummy-test-title");
    }

}
