package com.weno.problem.dto;

import com.weno.problem.Problem;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class ProblemRequestDto {
    private String title;

    public static Problem toEntity(ProblemRequestDto request) {
        return Problem.builder()
                .title(request.getTitle())
                .build();
    }
}
