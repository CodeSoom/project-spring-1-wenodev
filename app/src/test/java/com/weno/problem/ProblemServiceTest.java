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

    @BeforeEach
    void setUp(){
        problemRepository = mock(ProblemRepository.class);
        problemService = new ProblemService(problemRepository);
    }

    @Test
    void testGetAllProblems(){
        Problem problem = Problem.builder()
                .title("dummy-test-title")
                .build();

        List<Problem> problems = Arrays.asList(problem);
        given(problemRepository.findAll()).willReturn(problems);
        List<ProblemResponseDto> problemResponses = problemService.getAllProblems();

        assertThat(problemResponses.get(0).getTitle()).isEqualTo("dummy-test-title");
        verify(problemRepository).findAll();
    }

    @Test
    void testGetProblem(){
        Problem problem = Problem.builder()
                .title("dummy-test-title")
                .build();

        given(problemRepository.findById(1L)).willReturn(Optional.of(problem));
        ProblemResponseDto problemResponse = problemService.getProblem(1L);

        assertThat(problemResponse.getTitle()).isEqualTo("dummy-test-title");
        verify(problemRepository).findById(1L);
    }

}
