package com.weno.problem.dto;

import com.weno.problem.Problem;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class ProblemResponseDto {
    private Long id;
    private String title;

    public static ProblemResponseDto of(Problem problem){
        return ProblemResponseDto.builder()
                .id(problem.getId())
                .title(problem.getTitle())
                .build();
    }
}
