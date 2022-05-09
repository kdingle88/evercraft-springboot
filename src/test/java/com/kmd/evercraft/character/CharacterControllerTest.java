package com.kmd.evercraft.character;

import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.hamcrest.Matchers.containsString;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


class CharacterControllerTest {

    @Test
    public void addCharacter() throws Exception {
        CharacterService characterService = mock(CharacterService.class);

        Character mockCharacter = new Character(1L,"Keith","GOOD");

        when(characterService.addCharacter(any(Character.class))).thenReturn(mockCharacter);

        CharacterController characterController = new CharacterController(characterService);

        Character actualCharacter = characterController.createCharacter(new Character("Keith","GOOD"));

        assertEquals(mockCharacter,actualCharacter);
    }

}