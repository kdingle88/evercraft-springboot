package com.kmd.evercraft.character;

import org.junit.jupiter.api.Test;

import static com.kmd.evercraft.character.CharacterAlignment.GOOD;
import static com.kmd.evercraft.character.CharacterAlignment.NEUTRAL;
import static org.junit.jupiter.api.Assertions.*;

class RogueTest {

    @Test
    public void cantHaveGoodAlignment() throws Exception {

        Exception exception = assertThrows(Exception.class, () -> new Rogue(1L,"Jenny",GOOD));

        String expectedMessage = "Good Alignment invalid for Rogue";

        String actualMessage = exception.getMessage();

        assertEquals(actualMessage, expectedMessage);
    }

    @Test
    public void totalRollAddsDexModifier() throws Exception {
        Rogue rogue = new Rogue(1L,"Jenny",NEUTRAL);

        rogue.setDexterity(12);

        int naturalRoll = 10;

        assertEquals(11,rogue.getTotalRoll(naturalRoll));
    }

    @Test
    public void dexModifierIncreasesDamage() throws Exception {
        Rogue rogue = new Rogue(1L,"Jenny",NEUTRAL);

        rogue.setDexterity(12);

        assertEquals(2,rogue.calculateDamage());
    }
}