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
    @Test
    public void testAbilities() {
        Character character = new Character();

        assertEquals(10,character.getStrength());
        assertEquals(10,character.getDexterity());
        assertEquals(10,character.getConstitution());
        assertEquals(10,character.getWisdom());
        assertEquals(10,character.getIntelligence());
        assertEquals(10,character.getCharisma());
    }

    @Test
    public void testGetModifier() {

        assertEquals(-5,Character.getModifier(1));
        assertEquals(0,Character.getModifier(10));
        assertEquals(1,Character.getModifier(12));

    }

    @Test
    public void testAddDexterityModifierToArmor() {
        Character character = new Character();

        character.setDexterity(12);

        assertEquals(11,character.getArmor());
    }

    @Test
    public void testAddConstitutionModifierToHP() {
        Character character = new Character();

        character.setConstitution(15);

        assertEquals(7, character.getHitPoints());
    }

    @Test
    public void testStartingHPisAtLeastOne() {
        Character character = new Character();

        character.setConstitution(1);

        assertEquals(1,character.getHitPoints());
    }

    @Test
    public void testStartingLevel() {
        Character character = new Character();

        assertEquals(1, character.getLevel());
    }
    @Test
    public void testStartingXP() {
        Character character = new Character();

        assertEquals(0, character.getXP());
    }

}
