package com.kmd.evercraft.character;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.kmd.evercraft.character.AdventurerAlignment.GOOD;
import static com.kmd.evercraft.character.AdventurerAlignment.NEUTRAL;
import static org.junit.jupiter.api.Assertions.*;

class RogueTest {

    RogueBuilder rogueBuilder = new RogueBuilder();

    @BeforeEach
    public void resetBuilder() {
        rogueBuilder.reset();
    }

    @Test
    public void cantHaveGoodAlignment() throws Exception {
        String expectedMessage = "Good Alignment invalid for Rogue";

        Exception exception = assertThrows(Exception.class, () -> rogueBuilder
                .setName("Jenny")
                .setAlignment(GOOD)
                .build());
        String actualMessage = exception.getMessage();

        assertEquals(actualMessage, expectedMessage);
    }

    @Test
    public void totalRollAddsDexModifier() throws Exception {
        Rogue rogue = rogueBuilder.
                setId(1l)
                .setName("Jenny")
                .setAlignment(NEUTRAL)
                .setDexterity(12)
                .build();
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