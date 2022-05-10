package com.kmd.evercraft.character;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CharacterTest {

    @Test
    public void testArmor() {
        Character character = new Character();

        assertEquals(10,character.getArmor());
    }

    @Test
    public void testHitPoints() {
        Character character = new Character();

        assertEquals(5,character.getHitPoints());
    }

    @Test
    public void charactersFightHit() {
        Character character1 = new Character();
        Character character2 = new Character();
        int roll = 10;

        boolean isHit = character1.attack(character2,roll);

        assertEquals(true, isHit);
    }
}
