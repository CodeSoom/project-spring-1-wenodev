package com.weno.content.dto;

import com.weno.content.Content;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Getter
public class ContentRequestDto {
    private Long id;
    private String question;
    private String answer;
    private String userAnswer;

    @Builder
    public ContentRequestDto(Long id, String question, String answer, String userAnswer) {
        this.id = id;
        this.question = question;
        this.answer = answer;
        this.userAnswer = userAnswer;
    }

    public static Content toEntity(ContentRequestDto request){
        return Content.builder()
                .id(request.getId())
                .question(request.getQuestion())
                .answer(request.getAnswer())
                .userAnswer(request.getUserAnswer())
                .build();
    }

    public static List<Content> toEntityList(List<ContentRequestDto> contentRequestDtoList) {
        List<Content> contents = new ArrayList<>();
        for (ContentRequestDto contentRequestDto : contentRequestDtoList){
            contents.add(ContentRequestDto.toEntity(contentRequestDto));
        }
        return contents;
    }
}
