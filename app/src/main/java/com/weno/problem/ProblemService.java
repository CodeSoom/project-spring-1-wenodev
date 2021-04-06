package com.weno.problem;

import com.weno.problem.dto.ProblemRequestDto;
import com.weno.problem.dto.ProblemResponseDto;
import com.weno.problem.exception.ProblemNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Transactional
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
            responses.add(ProblemResponseDto.builder()
                    .title(problem.getTitle())
                    .build());
        }
        return responses;
    }

    public ProblemResponseDto getProblem(Long id) {
        Problem problem = problemRepository.findById(id).orElseThrow(()-> new ProblemNotFoundException("no problem id :" + id));
        return ProblemResponseDto.of(problem);
    }

    public ProblemResponseDto saveProblem(ProblemRequestDto request) {
        Problem problem = ProblemRequestDto.toEntity(request);
        problemRepository.save(problem);
        return ProblemResponseDto.of(problem);
    }

    public ProblemResponseDto updateProblem(Long id, ProblemRequestDto request) {
        Problem problem = problemRepository.findById(id).orElseThrow(()-> new ProblemNotFoundException("no problem id :" + id));
        problem.updateProblem(request);
        return ProblemResponseDto.of(problem);
    }

    public ProblemResponseDto deleteProblem(Long id) {
        Problem problem = problemRepository.findById(id).orElseThrow(()-> new ProblemNotFoundException("no problem id :" + id));
        problemRepository.delete(problem);
        return ProblemResponseDto.of(problem);
    }
}
