package com.weno.problem;

import com.weno.problem.dto.ProblemRequestDto;
import com.weno.problem.dto.ProblemResponseDto;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/problems")
@CrossOrigin
public class ProblemController {

    private final ProblemService problemService;

    public ProblemController(ProblemService problemService) {
        this.problemService = problemService;
    }

    @GetMapping
    public List<ProblemResponseDto> getAllProblems(){
        return problemService.getAllProblems();
    }

    @GetMapping("/{id}")
    public ProblemResponseDto getProblem(@PathVariable Long id){
        return problemService.getProblem(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("isAuthenticated()")
    public ProblemResponseDto saveProblem(@RequestBody ProblemRequestDto request){
        return problemService.saveProblem(request);
    }

    @PutMapping("/{id}")
    public ProblemResponseDto updateProblem(@PathVariable Long id, @RequestBody ProblemRequestDto request){
        return problemService.updateProblem(id, request);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public ProblemResponseDto deleteProblem(@PathVariable Long id){
        return problemService.deleteProblem(id);
    }
}
