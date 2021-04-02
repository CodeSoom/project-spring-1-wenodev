package com.weno.content.dto;

import com.weno.content.Content;
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

    public static ContentResponseDto of(Content content) {
        return ContentResponseDto.builder()
                .id(content.getId())
                .question(content.getQuestion())
                .answer(content.getAnswer())
                .userAnswer(content.getUserAnswer())
                .build();
    }
}
