package com.weno.problem;

import com.weno.auth.AuthService;
import com.weno.problem.dto.ProblemRequestDto;
import com.weno.problem.dto.ProblemResponseDto;
import com.weno.problem.exception.ProblemNotFoundException;
import org.hamcrest.core.StringContains;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
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
import java.util.Optional;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

//@AutoConfigureMockMvc
@WebMvcTest(ProblemController.class)
@DisplayName("ProblemController 테스트")
class ProblemControllerTest {
    private static final String VALID_TOKEN = "eyJhbGciOiJIUzI1NiJ9." +
            "eyJzdWIiOiJleGlzdGVkRW1haWwifQ.UQodS3elf3Cu4g0PDFHqVloFbcKHHmTTnk0jGmiwPXY";

    @Autowired
    private WebApplicationContext context;

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProblemService problemService;

    @MockBean
    private AuthService authService;

    private static final String BASE_URL = "/api/v1/problems";

    private static final Long NOT_EXISTED_ID = 100L;


    private static final Long EXISTED_ID = 1L;
    private static final String EXISTED_TITLE = "existedTitle";

    private Problem existedProblem;
    private ProblemResponseDto existedProblemResponseDto;

    @BeforeEach
    void setUp(){
        mockMvc = MockMvcBuilders
                .webAppContextSetup(context)
                .apply(springSecurity())
                .build();

        existedProblem = Problem.builder()
                .id(EXISTED_ID)
                .title(EXISTED_TITLE)
                .build();

        existedProblemResponseDto = ProblemResponseDto.of(existedProblem, null);
    }

    @Nested
    @DisplayName("list 메소드는")
    class Describe_list{
        @Test
        @DisplayName("전체 Problem 리스트와 OK를 리턴한다.")
        void itReturnProblemListAndOk() throws Exception {
            given(problemService.list()).willReturn(List.of(existedProblemResponseDto));

            mockMvc.perform(get(BASE_URL))
                    .andExpect(content().string(StringContains.containsString(EXISTED_TITLE)))
                    .andExpect(status().isOk());

            verify(problemService).list();
        }
    }

    @Nested
    @DisplayName("detail 메소드는")
    class Describe_detail{
        @Nested
        @DisplayName("id가 존재한다면")
        class Context_WithExistedId{
            @Test
            @DisplayName("Problem과 OK를 리턴한다.")
            void ItReturnProblemAndOk() throws Exception {
                given(problemService.detail(EXISTED_ID)).willReturn(existedProblemResponseDto);

                mockMvc.perform(get(BASE_URL + "/" + EXISTED_ID))
                        .andExpect(content().string(StringContains.containsString(EXISTED_TITLE)))
                        .andExpect(status().isOk());

                verify(problemService).detail(EXISTED_ID);
            }
        }
        @Nested
        @DisplayName("id가 존재하지 않는다면")
        class Context_WithNotExistedId{
            @Test
            @DisplayName("예외를 발생시키고 NOT_FOUND를 리턴한다.")
            void ItThrowProblemNotFoundException() throws Exception {
                given(problemService.detail(NOT_EXISTED_ID))
                        .willThrow(new ProblemNotFoundException("no problem id : " + NOT_EXISTED_ID));

                mockMvc.perform(get(BASE_URL + "/" + NOT_EXISTED_ID))
                        .andExpect(status().isNotFound());

                verify(problemService).detail(NOT_EXISTED_ID);
            }
        }
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
        mockMvc.perform(put(BASE_URL + "/" + EXISTED_ID)
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"title\" : \"dummy-test-title\"}"))
                .andExpect(status().isOk());
    }

    @Test
    void testDeleteProblem() throws Exception {
        mockMvc.perform(delete(BASE_URL + "/" + EXISTED_ID))
                .andExpect(status().isNoContent());
    }

}
