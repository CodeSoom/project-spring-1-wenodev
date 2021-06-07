package com.weno.problem;

import com.weno.auth.AuthService;
import com.weno.filters.errors.InvalidTokenException;
import com.weno.problem.dto.ProblemRequestDto;
import com.weno.problem.dto.ProblemResponseDto;
import com.weno.problem.exception.ProblemNotFoundException;
import org.hamcrest.core.StringContains;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
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

    private static final String NOT_VALID_TOKEN = "eyJhbGciOiJIUzI1NiJ9." +
            "eyJzdWIiOiJleGlzdGVkRW1haWwifQ.UQodS3elf3Cu4g0PDFHqVloFbcKHHmTTnk0jGmiwPXA";

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

    private static final Long NEW_ID = 2L;
    private static final String NEW_TITLE = "newTitle";
    private Problem newProblem;
    private ProblemResponseDto newProblemResponseDto;

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

        newProblem = Problem.builder()
                .id(NEW_ID)
                .title(NEW_TITLE)
                .build();

        newProblemResponseDto = ProblemResponseDto.of(newProblem, null);
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
            void ItReturnProblemAndOK() throws Exception {
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
            void ItThrowProblemNotFoundExceptionAndReturnNOT_FOUND() throws Exception {
                given(problemService.detail(NOT_EXISTED_ID))
                        .willThrow(new ProblemNotFoundException("no problem id : " + NOT_EXISTED_ID));

                mockMvc.perform(get(BASE_URL + "/" + NOT_EXISTED_ID))
                        .andExpect(status().isNotFound());

                verify(problemService).detail(NOT_EXISTED_ID);
            }
        }
    }

    @Nested
    @DisplayName("create 메소드는")
    class Describe_create{
        @Nested
        @DisplayName("만약 유요한 토큰값이 주어진다면")
        class Context_WithValidToken{
            @Test
            @DisplayName("Problem을 저장하고 저장된 상품과 CREATED를 리턴한다.")
            void ItSaveProblemAndReturnSavedProblemAndCREATED() throws Exception {
                given(authService.parseToken(VALID_TOKEN)).willReturn(VALID_TOKEN);

                mockMvc.perform(post(BASE_URL)
                        .contentType(MediaType.APPLICATION_JSON)
                        .header("Authorization", "Bearer " + VALID_TOKEN)
                        .content("{\"title\" : \"dummy-test-title\"}"))
                        .andExpect(status().isCreated());

                verify(problemService).create(any(ProblemRequestDto.class));
            }
        }

        @Nested
        @DisplayName("만약 유요하지 않은 토큰값이 주어진다면")
        class Context_WithNotValidToken{
            @Test
            @DisplayName("예외를 발생시키고 UNAUTHORIZED를 리턴하라")
            void ItThrowInvalidTokenExceptionAndReturnUNAUTHORIZED() throws Exception {
                given(authService.parseToken(NOT_VALID_TOKEN))
                        .willThrow(new InvalidTokenException(NOT_VALID_TOKEN));

                mockMvc.perform(post(BASE_URL)
                        .contentType(MediaType.APPLICATION_JSON)
                        .header("Authorization", "Bearer " + NOT_VALID_TOKEN)
                        .content("{\"title\" : \"dummy-test-title\"}"))
                        .andExpect(status().isUnauthorized());
            }
        }

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
