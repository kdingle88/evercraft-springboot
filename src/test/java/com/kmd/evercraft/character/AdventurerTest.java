package com.kmd.evercraft.character;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.kmd.evercraft.character.AdventurerAlignment.GOOD;
import static com.kmd.evercraft.character.AdventurerAlignment.NEUTRAL;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class AdventurerTest {

    AdventurerBuilder adventurerBuilder = new AdventurerBuilder();
    @BeforeEach
    public void resetBuilder() {
        adventurerBuilder.reset();
    }

    @Test
    public void setNameSetsCharactersName() {
        Adventurer adventurer = adventurerBuilder
                .setName("Bill")
                .build();

        assertEquals("Bill", adventurer.getName());
    }

    @Test
    public void setAlignmentSetsCharactersAlignment() throws Exception{
        Adventurer adventurer = adventurerBuilder
                .setAlignment(GOOD)
                .build();

        assertEquals(GOOD, adventurer.getAlignment());
    }

    @Test
    public void armorDefaultsToTen() {
        Adventurer adventurer = adventurerBuilder.build();

        assertEquals(10, adventurer.getArmor());
    }

    @Test
    public void hitPointsDefaultsToFive() {
        Adventurer adventurer = adventurerBuilder.build();

        assertEquals(5, adventurer.getHitPoints());
    }

    @Test
    public void dexterityModifierIncreasesArmor() {
        Adventurer adventurer = adventurerBuilder
                .setDexterity(12)
                .build();

        assertEquals(11, adventurer.getArmor());
    }

    @Test
    public void constitutionModifierIncreasesHP() {
        Adventurer adventurer = adventurerBuilder
                .setConstitution(15)
                .build();

        assertEquals(7, adventurer.getHitPoints());
    }

    @Test
    public void startingHPisAtLeastOne() {
        Adventurer adventurer = adventurerBuilder
                .setConstitution(1)
                .build();

        assertEquals(1, adventurer.getHitPoints());
    }

    @Test
    public void startingLevelIsOne() {
        Adventurer adventurer = adventurerBuilder.build();

        assertEquals(1, adventurer.getLevel());
    }
    @Test
    public void startingXPIsZero() {
        Adventurer adventurer = adventurerBuilder.build();

        assertEquals(0, adventurer.getXP());
    }

    @Test
    public void setXPWith1000XPIncreasesLevelByOne() {
        Adventurer adventurer = adventurerBuilder
                .setXP(1000)
                .build();

        assertEquals(2, adventurer.getLevel());
    }
    @Test
    public void setXPWith9000XPIncreasesNineLevels() {
        Adventurer adventurer = adventurerBuilder
                .setXP(9000)
                .build();

        assertEquals(10, adventurer.getLevel());
    }
    @Test
    public void hpIncreasesByFiveForEachLevelIncrease() {
        Adventurer adventurer = adventurerBuilder
                .setXP(1000)
                .build();

        assertEquals(10, adventurer.getHitPoints());
    }

    @Test
    public void hpDoesntIncreaseOnNegativeFiveModifier() {
        Adventurer adventurer = adventurerBuilder
                .setConstitution(1)
                .setXP(1000)
                .build();

        assertEquals(1, adventurer.getHitPoints());
    }

    @Test
    public void addXPWith1000IncreasesXPBy1000() {
        Adventurer adventurer = adventurerBuilder.build();
        adventurer.addXP(1000);

        assertEquals(1000, adventurer.getXP());
    }

    @Test
    public void add500XPTwiceTotals1000XP() {
        Adventurer adventurer = adventurerBuilder.build();
        adventurer.addXP(500);
        adventurer.addXP(500);

        assertEquals(1000, adventurer.getXP());
    }

    @Test
    public void addLevelWith1000XPIncreasesLevelByOne() {
        Adventurer adventurer = adventurerBuilder.build();
        adventurer.addLevel(1000);

        assertEquals(2, adventurer.getLevel());
    }

    @Test
    public void setXPOverwritesLevelAndXP() {
        Adventurer adventurer = adventurerBuilder
                .setXP(10000)
                .build();

        assertEquals(11, adventurer.getLevel());
        assertEquals(10000, adventurer.getXP());
    }

    @Test
    public void totalRollAddsStrengthModifier() {
        Adventurer adventurer = adventurerBuilder
                .setStrength(12)
                .build();

        int naturalRoll = 10;

        assertEquals(11, adventurer.getTotalRoll(naturalRoll));
    }

    @Test
    public void strengthModifierIncreasesDamage() {
        Adventurer adventurer = adventurerBuilder
                .setStrength(12)
                .build();

        assertEquals(2, adventurer.calculateDamage());
    }

    @Test
    public void adventurerCanTakeDamage() {
        Adventurer adventurer = adventurerBuilder.build();

        adventurer.takeDamage(1);

        assertEquals(4, adventurer.getHitPoints());
    }

    @Test
    public void builderReturnsCreatedAdventurer() throws Exception{
        AdventurerBuilder adventurerBuilder = new AdventurerBuilder();

        Adventurer adventurer = adventurerBuilder
                .setName("Bob")
                .setAlignment(GOOD)
                .build();


        assertEquals("Bob", adventurer.getName());
        assertEquals(GOOD, adventurer.getAlignment());



    }
}
