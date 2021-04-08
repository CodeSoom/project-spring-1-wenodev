package com.weno.problem.dto;

import com.weno.content.dto.ContentResponseDto;
import com.weno.problem.Problem;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@Getter
public class ProblemResponseDto {
    private Long id;
    private String title;
    private List<ContentResponseDto> contents;

    @Builder
    public ProblemResponseDto(Long id, String title, List<ContentResponseDto> contents) {
        this.id = id;
        this.title = title;
        this.contents = contents;
    }

    public static ProblemResponseDto of(Problem problem, List<ContentResponseDto> contents){
        return ProblemResponseDto.builder()
                .id(problem.getId())
                .title(problem.getTitle())
                .contents(contents)
                .build();
    }
}
