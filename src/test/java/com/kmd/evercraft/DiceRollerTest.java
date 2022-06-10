package com.kmd.evercraft;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import java.util.Random;

class DiceRollerTest {

    Random random= mock(Random.class, withSettings().withoutAnnotations());
    DiceRoller roller = new DiceRoller(random);


    @Test
    public void rollD20CalledWithProperBounds() {
        roller.rollD20();

        verify(random, times(1)).nextInt(1,21);
    }
}