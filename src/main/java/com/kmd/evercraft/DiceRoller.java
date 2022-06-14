package com.kmd.evercraft;

import java.util.Random;

public class DiceRoller {

    private Random random;

    public DiceRoller(Random random) {
        this.random = random;
    }

    public int rollD20() {
        return random.nextInt(1,21);
    }
}
