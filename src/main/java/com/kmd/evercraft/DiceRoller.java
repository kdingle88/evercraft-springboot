package com.kmd.evercraft;

public class DiceRoller {
    public static int rollD20() {
        return (int) (Math.random() * (20)) + 1;
    }
}
