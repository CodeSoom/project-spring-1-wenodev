package com.weno.problem.dto;

import com.weno.problem.Problem;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class ProblemRequestDto {
    private String title;

    @Builder
    public ProblemRequestDto(String title) {
        this.title = title;
    }

    public static Problem toEntity(ProblemRequestDto request) {
        return Problem.builder()
                .title(request.getTitle())
                .build();
    }
}
