package com.weno.problem;

import com.weno.problem.dto.ProblemResponseDto;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.List;

import static org.mockito.BDDMockito.given;

class ProblemServiceTest {

    @InjectMocks
    private ProblemService problemService;

    @Mock
    private ProblemRepository problemRepository;

    @Test
    void testGetAllProblems(){
        given(problemRepository.findAll()).willReturn(List.of());
    }

}
