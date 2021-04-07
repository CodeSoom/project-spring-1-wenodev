package com.weno.content;

import com.weno.content.dto.ContentRequestDto;
import com.weno.content.dto.ContentResponseDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
/*
1. getAllContent : 완료
2. saveContent : 완료
3. updateContent
4. deleteContent
 */
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

    @Test
    void testGetAllContents(){
        given(contentRepository.findAll()).willReturn(List.of(content));
        List<ContentResponseDto> contentResponseList = contentService.getAllContents();
        assertThat(contentResponseList.get(0).getId()).isEqualTo(EXISTED_ID);
        verify(contentRepository).findAll();
    }

    @Test
    void testSaveContent(){
        ContentRequestDto request = ContentRequestDto.builder()
                .answer("dummy-test-answer-new")
                .question("dummy-test-question-new")
                .userAnswer("dummy-test-userAnswer-new")
                .build();

        given(contentRepository.save(any(Content.class))).willReturn(content);
        ContentResponseDto contentResponse = contentService.saveContent(request);
        assertThat(contentResponse.getAnswer()).isEqualTo("dummy-test-answer-new");
    }

    @Test
    void testUpdateContent(){
        ContentRequestDto request = ContentRequestDto.builder()
                .answer("dummy-test-answer-update")
                .question("dummy-test-question-update")
                .userAnswer("dummy-test-userAnswer-update")
                .build();

        given(contentRepository.findById(EXISTED_ID)).willReturn(Optional.of(content));
        ContentResponseDto contentResponse = contentService.updateContent(EXISTED_ID, request);
        assertThat(contentResponse).isNull();
    }


}
