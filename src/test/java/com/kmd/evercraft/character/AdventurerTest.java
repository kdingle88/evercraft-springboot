package com.kmd.evercraft.character;

import org.junit.jupiter.api.Test;

import static com.kmd.evercraft.character.AdventurerAlignment.GOOD;
import static com.kmd.evercraft.character.AdventurerAlignment.NEUTRAL;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class AdventurerTest {

    @Test
    public void setNameSetsCharactersName() {
        Adventurer adventurer = new Adventurer();
        adventurer.setName("Bill");

        assertEquals("Bill", adventurer.getName());
    }

    @Test
    public void setAlignmentSetsCharactersAlignment() {
        Adventurer adventurer = new Adventurer();
        adventurer.setAlignment(GOOD);

        assertEquals(GOOD, adventurer.getAlignment());
    }

    @Test
    public void armorDefaultsToTen() {
        Adventurer adventurer = new Adventurer();

        assertEquals(10, adventurer.getArmor());
    }

    @Test
    public void hitPointsDefaultsToFive() {
        Adventurer adventurer = new Adventurer();

        assertEquals(5, adventurer.getHitPoints());
    }

    @Test
    public void abilitiesDefaultToTen() {
        Adventurer adventurer = new Adventurer();

        assertEquals(10, adventurer.getStrength());
        assertEquals(10, adventurer.getDexterity());
        assertEquals(10, adventurer.getConstitution());
        assertEquals(10, adventurer.getWisdom());
        assertEquals(10, adventurer.getIntelligence());
        assertEquals(10, adventurer.getCharisma());
    }

    @Test
    public void getModifierWithScoreReturnsModifier() {
        assertEquals(-5, Adventurer.getModifier(1));
        assertEquals(0, Adventurer.getModifier(10));
        assertEquals(1, Adventurer.getModifier(12));
    }

    @Test
    public void dexterityModifierIncreasesArmor() {
        Adventurer adventurer = new Adventurer();
        adventurer.setDexterity(12);

        assertEquals(11, adventurer.getArmor());
    }

    @Test
    public void constitutionModifierIncreasesHP() {
        Adventurer adventurer = new Adventurer();
        adventurer.setConstitution(15);

        assertEquals(7, adventurer.getHitPoints());
    }

    @Test
    public void startingHPisAtLeastOne() {
        Adventurer adventurer = new Adventurer();
        adventurer.setConstitution(1);

        assertEquals(1, adventurer.getHitPoints());
    }

    @Test
    public void startingLevelIsOne() {
        Adventurer adventurer = new Adventurer();

        assertEquals(1, adventurer.getLevel());
    }
    @Test
    public void startingXPIsZero() {
        Adventurer adventurer = new Adventurer();

        assertEquals(0, adventurer.getXP());
    }

    @Test
    public void setXPWith1000XPIncreasesLevelByOne() {
        Adventurer adventurer = new Adventurer();
        adventurer.setXP(1000);

        assertEquals(2, adventurer.getLevel());
    }
    @Test
    public void setXPWith9000XPIncreasesNineLevels() {
        Adventurer adventurer = new Adventurer();
        adventurer.setXP(9000);

        assertEquals(10, adventurer.getLevel());
    }
    @Test
    public void hpIncreasesByFiveForEachLevelIncrease() {
        Adventurer adventurer = new Adventurer();
        adventurer.setXP(1000);

        assertEquals(10, adventurer.getHitPoints());
    }

    @Test
    public void hpDoesntIncreaseOnNegativeFiveModifier() {
        Adventurer adventurer = new Adventurer();
        adventurer.setConstitution(1);
        adventurer.setXP(1000);

        assertEquals(1, adventurer.getHitPoints());
    }

    @Test
    public void addXPWith1000IncreasesXPBy1000() {
        Adventurer adventurer = new Adventurer();
        adventurer.addXP(1000);

        assertEquals(1000, adventurer.getXP());
    }

    @Test
    public void add500XPTwiceTotals1000XP() {
        Adventurer adventurer = new Adventurer();
        adventurer.addXP(500);
        adventurer.addXP(500);

        assertEquals(1000, adventurer.getXP());
    }

    @Test
    public void addLevelWith1000XPIncreasesLevelByOne() {
        Adventurer adventurer = new Adventurer();
        adventurer.addLevel(1000);

        assertEquals(2, adventurer.getLevel());
    }

    @Test
    public void setLevelOverwritesLevelAndXP() {
        Adventurer adventurer = new Adventurer();
        adventurer.setLevel(10);

        assertEquals(10, adventurer.getLevel());
        assertEquals(9000, adventurer.getXP());
    }

    @Test
    public void setXPOverwritesLevelAndXP() {
        Adventurer adventurer = new Adventurer();
        adventurer.setXP(10000);

        assertEquals(11, adventurer.getLevel());
        assertEquals(10000, adventurer.getXP());
    }

    @Test
    public void setXPAfterSetLevelOverwritesSetLevel() {
        Adventurer adventurer = new Adventurer();
        adventurer.setLevel(15);
        adventurer.setXP(10000);

        assertEquals(11, adventurer.getLevel());
        assertEquals(10000, adventurer.getXP());
    }

    @Test
    public void setLevelAfterSetXPOverwritesSetXP() {
        Adventurer adventurer = new Adventurer();
        adventurer.setXP(10000);
        adventurer.setLevel(15);

        assertEquals(15, adventurer.getLevel());
        assertEquals(14000, adventurer.getXP());
    }

    @Test
    public void totalRollAddsStrengthModifier() {
        Adventurer adventurer = new Adventurer(1L,"Jenny",NEUTRAL);
        adventurer.setStrength(12);
        int naturalRoll = 10;

        assertEquals(11, adventurer.getTotalRoll(naturalRoll));
    }

    @Test
    public void strengthModifierIncreasesDamage() {
        Adventurer adventurer = new Adventurer(1L,"Jenny",NEUTRAL);
        adventurer.setStrength(12);

        assertEquals(2, adventurer.calculateDamage());
    }
}
