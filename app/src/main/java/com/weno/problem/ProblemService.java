package com.weno.problem;

import com.weno.problem.dto.ProblemResponseDto;
import com.weno.problem.exception.ProblemNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
public class ProblemService {

    private final ProblemRepository problemRepository;

    public ProblemService(ProblemRepository problemRepository) {
        this.problemRepository = problemRepository;
    }

    public List<ProblemResponseDto> getAllProblems() {
        List<Problem> problems = problemRepository.findAll();
        List<ProblemResponseDto> responses = new ArrayList<>();
        for (Problem problem : problems){
            ProblemResponseDto response = ProblemResponseDto.builder()
                    .title(problem.getTitle())
                    .build();
            responses.add(response);
        }
        return responses;
    }

    public ProblemResponseDto getProblem(Long id) {
        Problem problem = problemRepository.findById(id).orElseThrow(()-> new ProblemNotFoundException("no problem id :" + id));
        return ProblemResponseDto.of(problem);
    }
}
