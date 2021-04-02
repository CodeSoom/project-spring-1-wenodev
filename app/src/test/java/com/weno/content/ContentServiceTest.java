package com.weno.content;

import org.junit.jupiter.api.BeforeEach;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

class ContentServiceTest {

    private ContentService contentService;
    private ContentRepository contentRepository;

    @BeforeEach
    void setUp() {
        contentRepository = mock(ContentRepository.class);
        contentService = new ContentService(contentRepository);
    }

}
