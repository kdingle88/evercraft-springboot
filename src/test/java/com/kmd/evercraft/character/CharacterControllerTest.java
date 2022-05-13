package com.kmd.evercraft.character;

import org.junit.jupiter.api.Test;

import java.util.List;


import static com.kmd.evercraft.character.CharacterAlignment.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;


class CharacterControllerTest {

    @Test
    public void addCharacter() throws Exception {
        CharacterService characterService = mock(CharacterService.class);

        Character mockCharacter = new Character(1L,"Keith",GOOD);

        when(characterService.addCharacter(any(Character.class))).thenReturn(mockCharacter);

        CharacterController characterController = new CharacterController(characterService);

        Character actualCharacter = characterController.createCharacter(new Character("Keith",GOOD));

        assertEquals(mockCharacter,actualCharacter);
    }

    @Test
    public void fightCharacters() {
        CharacterService characterService = mock(CharacterService.class);

        CharacterController characterController = new CharacterController(characterService);

        Character character1 = new Character(1L,"Bob",EVIL);
        Character character2 = new Character(2L,"Jen",NEUTRAL);

        characterController.fightCharacters(List.of(character1,character2));

        verify(characterService,times(1)).fightCharacters(eq(character1),eq(character2));

    }

}