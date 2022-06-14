package com.kmd.evercraft.character;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class FighterTest {

    FighterBuilder fighterBuilder = new FighterBuilder();

    @BeforeEach
    public void resetBuilder() {
        fighterBuilder.reset();
    }

    @Test
    public void fighterStartsWithTenHP() {
        Adventurer fighter = fighterBuilder.build();

        assertEquals(10,fighter.getHitPoints());
    }

    @Test
    public void fighterIncreaseHPByTenEveryLevel() {
        Adventurer fighter = fighterBuilder.setXP(1000).build();

        assertEquals(20,fighter.getHitPoints());
    }

}