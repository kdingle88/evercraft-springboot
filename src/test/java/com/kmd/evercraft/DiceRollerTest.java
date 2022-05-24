package com.kmd.evercraft;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DiceRollerTest {

    @Test
    public void rollD20() {
        assertTrue(DiceRoller.rollD20() <= 20);
        assertTrue(DiceRoller.rollD20() > 0);
    }

}