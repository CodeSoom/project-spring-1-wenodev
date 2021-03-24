package com.weno.problem;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/api/v1/problems")
@RestController
public class ProblemController {

    @GetMapping
    public void getAllProblems(){
    }
}
