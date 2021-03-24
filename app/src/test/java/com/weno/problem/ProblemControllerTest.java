package com.weno.problem;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ProblemController.class)
class ProblemControllerTest {

    @Autowired
    private MockMvc mockMvc;

    private String BASE_URL;

    @BeforeEach
    void setUp(){
        BASE_URL = "/api/v1/problems";
    }

    @Test
    void testGetAllProblems() throws Exception {
        mockMvc.perform(get(BASE_URL))
                .andExpect(status().isOk());
    }
}
