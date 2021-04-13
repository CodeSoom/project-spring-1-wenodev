package com.weno.problem.dto;

import com.weno.content.dto.ContentRequestDto;
import com.weno.problem.Problem;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@Getter
public class ProblemRequestDto {
    private String title;
    private List<ContentRequestDto> contents;

    @Builder
    public ProblemRequestDto(String title, List<ContentRequestDto> contents) {
        this.title = title;
        this.contents = contents;
    }

    public static Problem toEntity(ProblemRequestDto request) {
        return Problem.builder()
                .title(request.getTitle())
                .build();
    }
}
