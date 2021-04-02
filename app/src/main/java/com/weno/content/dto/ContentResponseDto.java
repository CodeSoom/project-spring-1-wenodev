package com.weno.content.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class ContentResponseDto {
    private Long id;
    private String question;
    private String answer;
    private String userAnswer;

    @Builder
    public ContentResponseDto(Long id, String question, String answer, String userAnswer) {
        this.id = id;
        this.question = question;
        this.answer = answer;
        this.userAnswer = userAnswer;
    }
}
