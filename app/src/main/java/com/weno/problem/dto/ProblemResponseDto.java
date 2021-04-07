package com.weno.problem.dto;

import com.weno.content.dto.ContentResponseDto;
import com.weno.problem.Problem;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Builder
@Getter
public class ProblemResponseDto {
    private Long id;
    private String title;
    private List<ContentResponseDto> contents;

    public static ProblemResponseDto of(Problem problem, List<ContentResponseDto> contents){
        return ProblemResponseDto.builder()
                .id(problem.getId())
                .title(problem.getTitle())
                .build();
    }
}
