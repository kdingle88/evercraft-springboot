package com.kmd.evercraft.character;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MonkTest {
    MonkBuilder monkBuilder = new MonkBuilder();

    @BeforeEach
    public void resetBuilder() {
        monkBuilder.reset();
    }

    @Test
    public void monkStartsWithSixHP() {
        Adventurer monk = monkBuilder.build();

        assertEquals(6,monk.getHitPoints());
    }

    @Test
    public void monkIncreaseHPBySixEveryLevel() {
        Adventurer monk = monkBuilder
                .setXP(1000)
                .build();

        assertEquals(12,monk.getHitPoints());
    }

    @Test
    public void positiveWisdomModifierIncreasesArmor() {
        Adventurer monk = monkBuilder
                .setWisdom(12)
                .build();


        assertEquals(11,monk.getArmor());
    }

    @Test
    public void negativeWisdomModifierKeepsArmorTheSame() {
        Adventurer monk = monkBuilder
                .setWisdom(9)
                .build();

        assertEquals(10,monk.getArmor());
    }
}