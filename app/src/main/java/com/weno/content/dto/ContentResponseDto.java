package com.weno.content.dto;

import com.weno.content.Content;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

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

    public static List<ContentResponseDto> ofList(List<Content> contents) {

        List<ContentResponseDto> contentResponseList = new ArrayList<>();
        for (Content content : contents){
            contentResponseList.add(ContentResponseDto.of(content));
        }
        return contentResponseList;
    }
}
