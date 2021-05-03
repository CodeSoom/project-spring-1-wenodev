package com.weno.problem;

import com.weno.auth.AuthService;
import com.weno.problem.dto.ProblemRequestDto;
import com.weno.problem.dto.ProblemResponseDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@WebMvcTest(ProblemController.class)
class ProblemControllerTest {
    private static final String VALID_TOKEN = "eyJhbGciOiJIUzI1NiJ9." +
            "eyJzdWIiOiJleGlzdGVkRW1haWwifQ.UQodS3elf3Cu4g0PDFHqVloFbcKHHmTTnk0jGmiwPXY";


    @Autowired
    private WebApplicationContext context;

    private MockMvc mockMvc;

    @MockBean
    private ProblemService problemService;

    @MockBean
    private AuthService authService;

    private static final Long EXITED_ID = 1L;
    private static final String BASE_URL = "/api/v1/problems";

    @BeforeEach
    void setUp(){
        mockMvc = MockMvcBuilders
                .webAppContextSetup(context)
                .apply(springSecurity())
                .build();

        Problem problem = Problem.builder()
                .id(EXITED_ID)
                .build();

        given(problemService.saveProblem(any(ProblemRequestDto.class)))
                .willReturn(ProblemResponseDto.of(problem, List.of()));

    }

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
        given(authService.parseToken(VALID_TOKEN)).willReturn(VALID_TOKEN);

        mockMvc.perform(post(BASE_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .header("Authorization", "Bearer" + VALID_TOKEN)
                .content("{\"title\" : \"dummy-test-title\"}"))
                .andExpect(status().isCreated());

//        verify(problemService).saveProblem(any(ProblemRequestDto.class));
    }

    @Test
    void testUpdateProblem() throws Exception {
        mockMvc.perform(put(BASE_URL + "/" + EXITED_ID)
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"title\" : \"dummy-test-title\"}"))
                .andExpect(status().isOk());
    }

    @Test
    void testDeleteProblem() throws Exception {
        mockMvc.perform(delete(BASE_URL + "/" + EXITED_ID))
                .andExpect(status().isNoContent());
    }

}
