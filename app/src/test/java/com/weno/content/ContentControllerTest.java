package com.weno.content;

import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

class ContentControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ContentService contentService;

    private final static Long EXISTED_ID = 1L;
    private Content content;

    @BeforeEach
    void setUp(){
        content = Content.builder()
            .id(EXISTED_ID)
            .answer("dummy-test-answer-existed")
            .question("dummy-test-question-existed")
            .userAnswer("dummy-test-userAnswer-existed")
            .build();
    }
    
}
