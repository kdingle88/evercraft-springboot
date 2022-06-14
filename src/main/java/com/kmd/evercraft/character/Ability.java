package com.kmd.evercraft.character;

public class Ability {

    int score = 10;

    public Ability() {
    }

    public Ability(int initialScore) {
        if(initialScore < 1 || initialScore > 20) {
            throw new IllegalArgumentException("Ability Score out of range. Must be between 1-20");
        }
        this.score = initialScore;
    }

    public int getScore() {
        return score;
    }

    public int getModifier() {
        return (int)Math.floor(score / 2) -5;
    }
}
