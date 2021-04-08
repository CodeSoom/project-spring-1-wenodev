package com.weno.problem;

import com.weno.content.Content;
import com.weno.content.ContentRepository;
import com.weno.content.dto.ContentRequestDto;
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
    private Content content;
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

        content = Content.builder()
                .id(EXISTED_ID)
                .answer("dummy-test-answer-existed")
                .question("dummy-test-question-existed")
                .userAnswer("dummy-test-userAnswer-existed")
                .problem(problem)
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

        ContentRequestDto contentRequestDto = ContentRequestDto.builder()
                .id(EXISTED_ID)
                .answer("dummy-test-answer-new")
                .question("dummy-test-question-new")
                .userAnswer("dummy-test-userAnswer-new")
                .build();

        ProblemRequestDto problemRequest = ProblemRequestDto.builder()
                .title("dummy-test-title-new")
                .contents(List.of(contentRequestDto))
                .build();

        given(problemRepository.save(any(Problem.class))).willReturn(problem);
        given(contentRepository.save(any(Content.class))).willReturn(content);
        ProblemResponseDto problemResponse = problemService.saveProblem(problemRequest);

        assertThat(problemResponse.getTitle()).isEqualTo("dummy-test-title-new");
        assertThat(problemResponse.getContents().get(0).getAnswer()).isEqualTo("dummy-test-answer-new");
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
