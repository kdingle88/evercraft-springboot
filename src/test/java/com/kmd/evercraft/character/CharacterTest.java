package com.kmd.evercraft.character;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CharacterTest {

    @Test
    public void armorDefaultsToTen() {
        Character character = new Character();

        assertEquals(10,character.getArmor());
    }

    @Test
    public void hitPointsDefaultsToFive() {
        Character character = new Character();

        assertEquals(5,character.getHitPoints());
    }

    @Test
    public void abilitiesDefaultToTen() {
        Character character = new Character();

        assertEquals(10,character.getStrength());
        assertEquals(10,character.getDexterity());
        assertEquals(10,character.getConstitution());
        assertEquals(10,character.getWisdom());
        assertEquals(10,character.getIntelligence());
        assertEquals(10,character.getCharisma());
    }

    @Test
    public void getModifierWithScoreReturnsModifier() {

        assertEquals(-5,Character.getModifier(1));
        assertEquals(0,Character.getModifier(10));
        assertEquals(1,Character.getModifier(12));

    }

    @Test
    public void dexterityModifierIncreasesArmor() {
        Character character = new Character();

        character.setDexterity(12);

        assertEquals(11,character.getArmor());
    }

    @Test
    public void constitutionModifierIncreasesHP() {
        Character character = new Character();

        character.setConstitution(15);

        assertEquals(7, character.getHitPoints());
    }

    @Test
    public void startingHPisAtLeastOne() {
        Character character = new Character();

        character.setConstitution(1);

        assertEquals(1,character.getHitPoints());
    }

    @Test
    public void startingLevelIsOne() {
        Character character = new Character();

        assertEquals(1, character.getLevel());
    }
    @Test
    public void startingXPIsZero() {
        Character character = new Character();

        assertEquals(0, character.getXP());
    }

    @Test
    public void setXPWith1000XPIncreasesLevelByOne() {
        Character character = new Character();

        character.setXP(1000);

        assertEquals(2,character.getLevel());
    }
    @Test
    public void setXPWith9000XPIncreasesNineLevels() {
        Character character = new Character();

        character.setXP(9000);

        assertEquals(10,character.getLevel());
    }
    @Test
    public void hpIncreasesByFiveForEachLevelIncrease() {
        Character character = new Character();

        character.setXP(1000);

        assertEquals(10, character.getHitPoints());
    }

    @Test
    public void hpDoesntIncreaseOnNegativeFiveModifier() {
        Character character = new Character();

        character.setConstitution(1);

        character.setXP(1000);

        assertEquals(1, character.getHitPoints());
    }

    @Test
    public void addXPWith1000IncreasesXPBy1000() {
        Character character = new Character();

        character.addXP(1000);

        assertEquals(1000, character.getXP());
    }

    @Test
    public void add500XPTwiceTotals1000XP() {
        Character character = new Character();

        character.addXP(500);
        character.addXP(500);

        assertEquals(1000, character.getXP());
    }

    @Test
    public void addLevelWith1000XPIncreasesLevelByOne() {
        Character character = new Character();

        character.addLevel(1000);

        assertEquals(2,character.getLevel());
    }

    @Test
    public void setLevelOverwritesLevelAndXP() {
        Character character = new Character();

        character.setLevel(10);

        assertEquals(10, character.getLevel());
        assertEquals(9000, character.getXP());
    }

    @Test
    public void setXPOverwritesLevelAndXP() {
        Character character = new Character();

        character.setXP(10000);

        assertEquals(11, character.getLevel());
        assertEquals(10000, character.getXP());
    }

    @Test
    public void setXPAfterSetLevelOverwritesSetLevel() {
        Character character = new Character();

        character.setLevel(15);
        character.setXP(10000);

        assertEquals(11, character.getLevel());
        assertEquals(10000, character.getXP());
    }

    @Test
    public void setLevelAfterSetXPOverwritesSetXP() {
        Character character = new Character();

        character.setXP(10000);
        character.setLevel(15);

        assertEquals(15, character.getLevel());
        assertEquals(14000, character.getXP());
    }
}
