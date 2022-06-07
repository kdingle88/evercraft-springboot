package com.kmd.evercraft.character;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MonkTest {
    @Test
    public void monkStartsWithSixHP() {
        Character monk = new Monk();

        assertEquals(6,monk.getHitPoints());
    }

    @Test
    public void monkIncreaseHPBySixEveryLevel() {
        Character monk = new Monk();

        monk.setXP(1000);

        assertEquals(12,monk.getHitPoints());
    }

    @Test
    public void positiveWisdomModifierIncreasesArmor() {
        Character monk = new Monk();

        monk.setWisdom(12);

        assertEquals(11,monk.getArmor());
    }

    @Test
    public void negativeWisdomModifierKeepsArmorTheSame() {
        Character monk = new Monk();

        monk.setWisdom(9);

        assertEquals(10,monk.getArmor());
    }
}