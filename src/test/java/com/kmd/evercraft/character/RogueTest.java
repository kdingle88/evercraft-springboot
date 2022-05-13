package com.kmd.evercraft.character;

import org.junit.jupiter.api.Test;

import static com.kmd.evercraft.character.CharacterAlignment.GOOD;
import static org.junit.jupiter.api.Assertions.*;

class RogueTest {

    @Test
    public void cantHaveGoodAlignment() throws Exception {

        Exception exception = assertThrows(Exception.class, () -> new Rogue(1L,"Jenny",GOOD));

        String expectedMessage = "Good Alignment invalid for Rogue";

        String actualMessage = exception.getMessage();

        assertEquals(actualMessage, expectedMessage);
    }

}