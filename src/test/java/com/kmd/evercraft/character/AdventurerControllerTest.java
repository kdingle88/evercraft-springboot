package com.kmd.evercraft.character;

import org.junit.jupiter.api.Test;


import java.util.ArrayList;
import java.util.List;

import static com.kmd.evercraft.character.AdventurerAlignment.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;


class AdventurerControllerTest {

    @Test
    public void addCharacter() {
        AdventurerService adventurerService = mock(AdventurerService.class);
        Adventurer mockAdventurer = new Adventurer(1L,"Keith",GOOD);
        when(adventurerService.addCharacter(any(Adventurer.class))).thenReturn(mockAdventurer);
        AdventurerController adventurerController = new AdventurerController(adventurerService);

        Adventurer actualAdventurer = adventurerController.createCharacter(new Adventurer("Keith",GOOD));

        assertEquals(mockAdventurer, actualAdventurer);
    }

    @Test
    public void fightCharacters() {
        AdventurerService adventurerService = mock(AdventurerService.class);
        AdventurerController adventurerController = new AdventurerController(adventurerService);

        ArrayList<Long> characterIds = new ArrayList<>();

        characterIds.add(1L);
        characterIds.add(2L);

        adventurerController.fightCharacters(characterIds);

        verify(adventurerService,times(1)).fightCharacters(eq(1L),eq(2L));
    }
}