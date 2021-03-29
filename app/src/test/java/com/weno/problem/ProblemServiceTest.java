package com.weno.problem;

import com.weno.problem.dto.ProblemResponseDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

class ProblemServiceTest {

    private ProblemRepository problemRepository;
    private ProblemService problemService;
    private Problem problem;
    private static final Long EXISTED_ID = 1L;

    @BeforeEach
    void setUp(){
        problemRepository = mock(ProblemRepository.class);
        problemService = new ProblemService(problemRepository);
        problem = Problem.builder()
                .title("dummy-test-title")
                .build();

    }

    @Test
    void testGetAllProblems(){
        List<Problem> problems = Arrays.asList(problem);
        given(problemRepository.findAll()).willReturn(problems);
        List<ProblemResponseDto> problemResponses = problemService.getAllProblems();

        assertThat(problemResponses.get(0).getTitle()).isEqualTo("dummy-test-title");
        verify(problemRepository).findAll();
    }

    @Test
    void testGetProblem(){
        given(problemRepository.findById(EXISTED_ID)).willReturn(Optional.of(problem));
        ProblemResponseDto problemResponse = problemService.getProblem(EXISTED_ID);

        assertThat(problemResponse.getTitle()).isEqualTo("dummy-test-title");
        verify(problemRepository).findById(EXISTED_ID);
    }

}
