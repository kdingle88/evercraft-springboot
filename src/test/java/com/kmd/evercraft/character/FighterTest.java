package com.kmd.evercraft.character;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class FighterTest {

    @Test
    public void fighterStartsWithTenHP() {
        Adventurer fighter = new Fighter();

        assertEquals(10,fighter.getHitPoints());
    }

    @Test
    public void fighterIncreaseHPByTenEveryLevel() {
        Adventurer fighter = new Fighter();
        fighter.setXP(1000);

        fighter.updateCharacter();

        assertEquals(20,fighter.getHitPoints());
    }

}