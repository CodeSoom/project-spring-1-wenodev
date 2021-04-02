package com.weno.content;

import com.weno.content.dto.ContentResponseDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

class ContentServiceTest {

    private ContentService contentService;
    private ContentRepository contentRepository;
    private Content content;
    private final Long EXISTED_ID = 1L;

    @BeforeEach
    void setUp() {
        contentRepository = mock(ContentRepository.class);
        contentService = new ContentService(contentRepository);
        content = Content.builder()
                .id(EXISTED_ID)
                .answer("dummy-test-answer-existed")
                .question("dummy-test-question-existed")
                .userAnswer("dummy-test-userAnswer-existed")
                .build();
    }

    @Test
    void testGetContent(){
        given(contentRepository.findById(EXISTED_ID)).willReturn(Optional.of(content));
        ContentResponseDto contentResponseDto = contentService.getContent(EXISTED_ID);
        assertThat(contentResponseDto.getAnswer()).isEqualTo("dummy-test-answer-existed");
        verify(contentRepository).findById(EXISTED_ID);
    }

}
