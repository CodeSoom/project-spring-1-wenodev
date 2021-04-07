package com.weno.content.dto;

import com.weno.content.Content;
import lombok.Getter;

@Getter
public class ContentRequestDto {
    private Long id;
    private String question;
    private String answer;
    private String userAnswer;

    public static Content toEntity(ContentRequestDto request){
        return Content.builder()
                .id(request.getId())
                .question(request.getQuestion())
                .answer(request.getAnswer())
                .userAnswer(request.getUserAnswer())
                .build();
    }
}
