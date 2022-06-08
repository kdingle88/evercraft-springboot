package com.kmd.evercraft;

import java.util.Random;

public class DiceRoller {
    public int rollD20() {
        Random random = new Random();

        return random.nextInt(1,21);
    }
}
