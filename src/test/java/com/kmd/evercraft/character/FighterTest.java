package com.kmd.evercraft.character;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

class FighterTest {

    @Test
    public void fighterStartsWithTenHP() {
        Character fighter = new Fighter();

        assertTrue(fighter.getHitPoints() == 10);
    }

    @Test
    public void fighterIncreaseHPByTenEveryLevel() {
        Character fighter = new Fighter();

        fighter.setXP(1000);

        assertTrue(fighter.getHitPoints() == 20);
    }

}