package com.kmd.evercraft.character;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MonkTest {
    @Test
    public void monkStartsWithSixHP() {
        Character monk = new Monk();

        assertTrue(monk.getHitPoints() == 6);
    }

    @Test
    public void monkIncreaseHPBySixEveryLevel() {
        Character monk = new Monk();

        monk.setXP(1000);

        assertTrue(monk.getHitPoints() == 12);
    }

    @Test
    public void positiveWisdomModifierIncreasesArmor() {
        Character monk = new Monk();

        monk.setWisdom(12);

        assertTrue(monk.getArmor() == 11);
    }

    @Test
    public void negativeWisdomModifierKeepsArmorTheSame() {
        Character monk = new Monk();

        monk.setWisdom(9);

        assertTrue(monk.getArmor() == 11);
    }

}