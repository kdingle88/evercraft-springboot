package com.kmd.evercraft.character;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.hamcrest.Matchers.containsString;
import static org.mockito.ArgumentMatchers.any;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(CharacterController.class)
class CharacterControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    CharacterService characterService;

    @Test
    public void addCharacter() throws Exception {
        Character mockCharacter = new Character(1L,"Cloud","GOOD");

        when(characterService.addCharacter(any(Character.class))).thenReturn(mockCharacter);

        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .post("/api/v1/characters")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"id\":1,\"name\":\"Cloud\",\"alignment\":\"GOOD\"}");

        this.mockMvc.perform(requestBuilder)
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("{\"id\":1,\"name\":\"Cloud\",\"alignment\":\"GOOD\"}")));
    }

}