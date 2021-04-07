package com.weno.content;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
/*
1. getAllContent
2. saveContent
3. updateContent
4. deleteContent
 */
@WebMvcTest(ContentController.class)
class ContentControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ContentService contentService;

    private final static Long EXISTED_ID = 1L;
    private final static String BASE_URL = "/api/v1/content";
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

    @Test
    void testGetContent() throws Exception {
        mockMvc.perform(get(BASE_URL + "/" + EXISTED_ID))
                .andExpect(status().isOk());
    }

    @Test
    void testGetAllContent() throws Exception {
        mockMvc.perform(get(BASE_URL))
                .andExpect(status().isOk());
    }

}
