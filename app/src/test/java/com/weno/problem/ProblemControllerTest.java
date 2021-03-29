package com.weno.problem;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ProblemController.class)
class ProblemControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProblemService problemService;

    private static final Long EXITED_ID = 1L;
    private static final String BASE_URL = "/api/v1/problems";

    @Test
    void testGetAllProblems() throws Exception {
        mockMvc.perform(get(BASE_URL))
                .andExpect(status().isOk());
    }

    @Test
    void testGetProblem() throws Exception {
        mockMvc.perform(get(BASE_URL + "/" + EXITED_ID))
                .andExpect(status().isOk());
    }

    @Test
    void testSaveProblem() throws Exception {
        mockMvc.perform(post(BASE_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"title\" : \"dummy-test-title\"}"))
                .andExpect(status().isCreated());
    }
}
