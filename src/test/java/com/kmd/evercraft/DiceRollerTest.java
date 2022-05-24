package com.kmd.evercraft;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DiceRollerTest {

    DiceRoller roller = new DiceRoller();

    @Test
    public void rollD20() {
        assertTrue(roller.rollD20() <= 20);
        assertTrue(roller.rollD20() > 0);
    }

}