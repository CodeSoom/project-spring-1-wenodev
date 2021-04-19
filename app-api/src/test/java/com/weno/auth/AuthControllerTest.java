package com.weno.auth;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(AuthController.class)
class AuthControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AuthService authService;

    @Test
    void testLogin() throws Exception {
        mockMvc.perform(post("/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content("    {\n" +
                        "        \"email\" : \"weno@codesoom.com\",\n" +
                        "        \"password\" : \"code1234soom\"\n" +
                        "    }"))
               .andExpect(status().isCreated());
    }


    @Test
    void testCreate() throws Exception {
        mockMvc.perform(post("/register")
                .contentType(MediaType.APPLICATION_JSON)
                .content("    {\n" +
                        "        \"email\" : \"weno@codesoom.com\",\n" +
                        "        \"name\" : \"weno\",\n" +
                        "        \"password\" : \"code1234soom\"\n" +
                        "    }"))
                .andExpect(status().isCreated());
    }

}
