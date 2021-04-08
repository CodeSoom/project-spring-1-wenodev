package com.weno.problem;

import com.weno.content.Content;
import com.weno.content.ContentRepository;
import com.weno.content.dto.ContentRequestDto;
import com.weno.content.dto.ContentResponseDto;
import com.weno.content.exception.ContentNotFoundException;
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
    private final ContentRepository contentRepository;

    public ProblemService(ProblemRepository problemRepository, ContentRepository contentRepository) {
        this.problemRepository = problemRepository;
        this.contentRepository = contentRepository;
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
        List<Content> contents = contentRepository.findAllByProblem(problem);
        return ProblemResponseDto.of(problem, ContentResponseDto.ofList(contents));
    }

    public ProblemResponseDto saveProblem(ProblemRequestDto request) {
        Problem problem = ProblemRequestDto.toEntity(request);
        problemRepository.save(problem);

        List<Content> contents = ContentRequestDto.toEntityList(request.getContents());


        return ProblemResponseDto.of(problem, ContentResponseDto.ofList(contents));
    }

    public ProblemResponseDto updateProblem(Long id, ProblemRequestDto request) {
        Problem problem = problemRepository.findById(id).orElseThrow(()-> new ProblemNotFoundException("no problem id :" + id));
        problem.updateProblem(request);
        List<Content> contents = contentRepository.findAllByProblem(problem);
        return ProblemResponseDto.of(problem, ContentResponseDto.ofList(contents));    }

    public ProblemResponseDto deleteProblem(Long id) {
        Problem problem = problemRepository.findById(id).orElseThrow(()-> new ProblemNotFoundException("no problem id :" + id));
        problemRepository.delete(problem);
        List<Content> contents = contentRepository.findAllByProblem(problem);
        return ProblemResponseDto.of(problem, ContentResponseDto.ofList(contents));    }
}
