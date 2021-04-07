package com.weno.problem;

import com.weno.content.ContentRepository;
import com.weno.problem.dto.ProblemRequestDto;
import com.weno.problem.dto.ProblemResponseDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

class ProblemServiceTest {

    private ContentRepository contentRepository;
    private ProblemRepository problemRepository;
    private ProblemService problemService;
    private Problem problem;
    private static final Long EXISTED_ID = 1L;

    @BeforeEach
    void setUp(){
        contentRepository = mock(ContentRepository.class);
        problemRepository = mock(ProblemRepository.class);
        problemService = new ProblemService(problemRepository, contentRepository);
        problem = Problem.builder()
                .id(EXISTED_ID)
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

    @Test
    void testSaveProblem(){
        ProblemRequestDto problemRequest = ProblemRequestDto.builder()
                .title("dummy-test-title-2")
                .build();

        given(problemRepository.save(any(Problem.class))).willReturn(problem);
        ProblemResponseDto problemResponse = problemService.saveProblem(problemRequest);

        assertThat(problemResponse.getTitle()).isEqualTo("dummy-test-title-2");
        verify(problemRepository).save(any(Problem.class));
    }

    @Test
    void testUpdateProblem(){
        ProblemRequestDto problemRequest = ProblemRequestDto.builder()
                .title("dummy-test-title-3")
                .build();

        given(problemRepository.findById(EXISTED_ID)).willReturn(Optional.of(problem));
        ProblemResponseDto problemResponse = problemService.updateProblem(EXISTED_ID, problemRequest);

        assertThat(problemResponse.getId()).isEqualTo(EXISTED_ID);
        assertThat(problemResponse.getTitle()).isEqualTo("dummy-test-title-3");
    }

    @Test
    void testDeleteProblem(){
        given(problemRepository.findById(EXISTED_ID)).willReturn(Optional.of(problem));
        ProblemResponseDto problemResponse = problemService.deleteProblem(EXISTED_ID);

        assertThat(problemResponse.getId()).isEqualTo(EXISTED_ID);
    }
}
