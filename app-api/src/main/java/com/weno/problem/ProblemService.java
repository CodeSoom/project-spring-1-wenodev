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

    public List<ProblemResponseDto> list() {
        List<Problem> problems = problemRepository.findAll();
        List<ProblemResponseDto> responses = new ArrayList<>();
        for (Problem problem : problems){
            List<Content> contents = contentRepository.findAllByProblem(problem);
            responses.add(ProblemResponseDto.of(problem, ContentResponseDto.ofList(contents)));
        }
        return responses;
    }

    public ProblemResponseDto detail(Long id) {
        Problem problem = problemRepository.findById(id).orElseThrow(()-> new ProblemNotFoundException("no problem id :" + id));
        List<Content> contents = contentRepository.findAllByProblem(problem);
        return ProblemResponseDto.of(problem, ContentResponseDto.ofList(contents));
    }

    public ProblemResponseDto create(ProblemRequestDto request) {
        Problem problem = ProblemRequestDto.toEntity(request);
        problemRepository.save(problem);

        List<Content> contents = ContentRequestDto.toEntityList(request.getContents());
        for (Content content : contents){
            content.updateProblem(problem);
        }
        contentRepository.saveAll(contents);

        return ProblemResponseDto.of(problem, ContentResponseDto.ofList(contents));
    }

    public ProblemResponseDto update(Long id, ProblemRequestDto request) {
        Problem problem = problemRepository.findById(id).orElseThrow(()-> new ProblemNotFoundException("no problem id :" + id));
        problem.updateAll(request.getTitle());

        List<Content> contents = ContentRequestDto.toEntityList(request.getContents());
        for (Content newContent : contents){
            Content content = contentRepository.findById(newContent.getId()).orElseThrow(()->new ContentNotFoundException("no content id : " + newContent.getId()));
            content.updateContent(newContent.getQuestion(), newContent.getAnswer(), newContent.getUserAnswer());
            contentRepository.save(content);
        }

        return ProblemResponseDto.of(problem, ContentResponseDto.ofList(contents));
    }

    public ProblemResponseDto delete(Long id) {
        Problem problem = problemRepository.findById(id).orElseThrow(()-> new ProblemNotFoundException("no problem id :" + id));
        problemRepository.delete(problem);
        List<Content> contents = contentRepository.findAllByProblem(problem);
        return ProblemResponseDto.of(problem, ContentResponseDto.ofList(contents));
    }
}
