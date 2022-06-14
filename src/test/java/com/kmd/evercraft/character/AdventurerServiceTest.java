package com.kmd.evercraft.character;

import com.kmd.evercraft.DiceRoller;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static com.kmd.evercraft.character.AdventurerAlignment.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AdventurerServiceTest {
    AdventurerBuilder adventurerBuilder = new AdventurerBuilder();
    FighterBuilder fighterBuilder = new FighterBuilder();
    RogueBuilder rogueBuilder = new RogueBuilder();

    @BeforeEach
    public void resetBuilder() {adventurerBuilder.reset();}

    @InjectMocks
    @Spy
    AdventurerService adventurerService = new AdventurerService();

    @Mock
    AdventurerRepository adventurerRepository;

    @Mock
    DiceRoller roller;

    @Test
    public void fightCharactersFindsCharacters() throws Exception{
        List<Adventurer> adventurers = generateCharacters();
        when(adventurerRepository.findById(1L)).thenReturn(Optional.of(adventurers.get(0)));
        when(adventurerRepository.findById(2L)).thenReturn(Optional.of(adventurers.get(1)));

        adventurerService.fightCharacters(1L, 2L);

        verify(adventurerRepository,times(1)).findById(eq(adventurers.get(0).getId()));
        verify(adventurerRepository, times(1)).findById(eq(adventurers.get(1).getId()));
    }

    @Test
    public void fightCharactersSavesUpdatedCharacters() throws Exception{
        List<Adventurer> adventurers = generateCharacters();
        when(adventurerRepository.findById(1L)).thenReturn(Optional.of(adventurers.get(0)));
        when(adventurerRepository.findById(2L)).thenReturn(Optional.of(adventurers.get(1)));

        adventurerService.fightCharacters(1L, 2L);

        verify(adventurerRepository,times(1)).save(eq(adventurers.get(0)));
        verify(adventurerRepository, times(1)).save(eq(adventurers.get(1)));
    }
    @Test
    public void fightCharactersDeletesCharactersWhenHPIsZero() throws Exception{
        List<Adventurer> adventurers = generateCharacters();
        when(adventurerRepository.findById(1L)).thenReturn(Optional.of(adventurers.get(0)));
        when(adventurerRepository.findById(3L)).thenReturn(Optional.of(adventurers.get(2)));

        adventurerService.fightCharacters(1L, 3L);

        verify(adventurerRepository,times(1)).delete(eq(adventurers.get(2)));
        verify(adventurerRepository,times(1)).save(eq(adventurers.get(0)));
        verify(adventurerRepository,times(0)).save(eq(adventurers.get(2)));
    }

    @Test
    public void fightHigherRollThanAttackedArmorLowersHP() throws Exception{
        List<Adventurer> adventurers = generateCharacters();
        Adventurer attackingAdventurer = adventurers.get(0);
        Adventurer attackedAdventurer = adventurers.get(1);
        when(roller.rollD20()).thenReturn(11);

        List<Adventurer> updatedAdventurers = adventurerService.fight(attackingAdventurer, attackedAdventurer);
        Adventurer damagedAdventurer = updatedAdventurers.get(1);

        assertEquals(4, damagedAdventurer.getHitPoints());
    }

    @Test
    public void fightEqualRollToAttackedArmorLowersHP() throws Exception{
        List<Adventurer> adventurers = generateCharacters();
        Adventurer attackingAdventurer = adventurers.get(0);
        Adventurer attackedAdventurer = adventurers.get(1);
        when(roller.rollD20()).thenReturn(10);

        List<Adventurer> updatedAdventurers = adventurerService.fight(attackingAdventurer, attackedAdventurer);
        Adventurer damagedAdventurer = updatedAdventurers.get(1);

        assertEquals(4, damagedAdventurer.getHitPoints());
    }

    @Test
    public void fightLowerRollThanAttackedArmorKeepsHPTheSame() throws Exception{
        List<Adventurer> adventurers = generateCharacters();
        Adventurer attackingAdventurer = adventurers.get(0);
        Adventurer attackedAdventurer = adventurers.get(1);
        when(roller.rollD20()).thenReturn(9);

        List<Adventurer> updatedAdventurers = adventurerService.fight(attackingAdventurer, attackedAdventurer);
        Adventurer notDamagedAdventurer = updatedAdventurers.get(1);

        assertEquals(5, notDamagedAdventurer.getHitPoints());
    }

    @Test
    public void fightCriticalRollLowersAttackedHpByDouble() throws Exception{
        List<Adventurer> adventurers = generateCharacters();
        Adventurer attackingAdventurer = adventurers.get(0);
        Adventurer attackedAdventurer = adventurers.get(1);
        when(roller.rollD20()).thenReturn(20);

        List<Adventurer> updatedAdventurers = adventurerService.fight(attackingAdventurer, attackedAdventurer);
        Adventurer damagedAdventurer = updatedAdventurers.get(1);

        assertEquals(3, damagedAdventurer.getHitPoints());
    }

    @Test
    public void attackingStrengthModifierIncreasesTotalRoll() throws Exception{
        List<Adventurer> adventurers = generateCharacters();
        Adventurer attackingAdventurer = adventurers.get(0);
        attackingAdventurer.setStrength(12);
        int roll = 9;

        int rollWithModifer = attackingAdventurer.getTotalRoll(roll);

        assertEquals(10,rollWithModifer);
    }

    @Test
    public void minimumDamageIsOneOnCritical() throws Exception{
        List<Adventurer> adventurers = generateCharacters();
        Adventurer attackingAdventurer = adventurers.get(0);
        Adventurer attackedAdventurer = adventurers.get(1);
        when(roller.rollD20()).thenReturn(20);
        attackingAdventurer.setStrength(1);
        attackedAdventurer.setArmor(21);

        List<Adventurer> updatedAdventurers = adventurerService.fight(attackingAdventurer, attackedAdventurer);

        Adventurer damagedAdventurer = updatedAdventurers.get(1);

        assertEquals(4, damagedAdventurer.getHitPoints());
    }

    @Test
    public void attackIncreaseWithEvenLevel() throws Exception{
        List<Adventurer> adventurers = generateCharacters();
        Adventurer attackingAdventurer = adventurers.get(0);
        Adventurer attackedAdventurer = adventurers.get(1);
        when(roller.rollD20()).thenReturn(9);
        attackingAdventurer.setXP(3000);
        attackingAdventurer.updateCharacter();

        List<Adventurer> updatedAdventurers = adventurerService.fight(attackingAdventurer, attackedAdventurer);
        Adventurer damagedAdventurer = updatedAdventurers.get(1);

        assertEquals(4, damagedAdventurer.getHitPoints());
    }
    @Test
    public void attackStaysWithOddLevel() throws Exception{
        List<Adventurer> adventurers = generateCharacters();
        Adventurer attackingAdventurer = adventurers.get(0);
        Adventurer attackedAdventurer = adventurers.get(1);
        when(roller.rollD20()).thenReturn(8);
        attackingAdventurer.setXP(2000);

        List<Adventurer> updatedAdventurers = adventurerService.fight(attackingAdventurer, attackedAdventurer);
        Adventurer damagedAdventurer = updatedAdventurers.get(1);

        assertEquals(5, damagedAdventurer.getHitPoints());
    }
    @Test
    public void fighterAttackIncreaseEveryLevelIncrease() {
        List<Adventurer> fighters = generateFighters();
        when(roller.rollD20()).thenReturn(8);
        Adventurer attacking = fighters.get(0);
        Adventurer attacked = fighters.get(1);
        attacking.setXP(2000);
        attacking.updateCharacter();

        List<Adventurer> updatedAdventurers = adventurerService.fight(attacking, attacked);
        Adventurer damaged = updatedAdventurers.get(1);

        assertEquals(9,damaged.getHitPoints());
    }

    @Test
    public void onHitAttackerGainsTenXP() throws Exception{
        List<Adventurer> adventurers = generateCharacters();
        Adventurer attackingAdventurer = adventurers.get(0);
        Adventurer attackedAdventurer = adventurers.get(1);
        when(roller.rollD20()).thenReturn(11);

        List<Adventurer> updatedAdventurers = adventurerService.fight(attackingAdventurer, attackedAdventurer);
        Adventurer adventurer = updatedAdventurers.get(0);

        assertEquals(10, adventurer.getXP());
    }

    @Test
    public void onMissAttackerXPStaysTheSame() throws Exception{
        List<Adventurer> adventurers = generateCharacters();
        Adventurer attackingAdventurer = adventurers.get(0);
        Adventurer attackedAdventurer = adventurers.get(1);
        when(roller.rollD20()).thenReturn(9);

        List<Adventurer> updatedAdventurers = adventurerService.fight(attackingAdventurer, attackedAdventurer);
        Adventurer adventurer = updatedAdventurers.get(0);

        assertEquals(0, adventurer.getXP());
    }

    @Test
    public void attackerCanLevelUpWhenGainingXP() throws Exception{
        List<Adventurer> adventurers = generateCharacters();
        Adventurer attackingAdventurer = adventurers.get(0);
        Adventurer attackedAdventurer = adventurers.get(1);
        when(roller.rollD20()).thenReturn(11);
        attackingAdventurer.setXP(990);

        List<Adventurer> updatedAdventurers = adventurerService.fight(attackingAdventurer, attackedAdventurer);
        Adventurer adventurer = updatedAdventurers.get(0);
        adventurer.updateCharacter();

        assertEquals(1000, adventurer.getXP());
        assertEquals(2, adventurer.getLevel());
    }

    @Test
    public void onTwoHitsAttackerXPIncreasesTwenty() throws Exception{
        List<Adventurer> adventurers = generateCharacters();
        Adventurer attackingAdventurer = adventurers.get(0);
        Adventurer attackedAdventurer = adventurers.get(1);
        when(roller.rollD20()).thenReturn(11);

        List<Adventurer> updatedAdventurers = adventurerService.fight(attackingAdventurer, attackedAdventurer);
        updatedAdventurers = adventurerService.fight(updatedAdventurers.get(0), updatedAdventurers.get(1));
        Adventurer adventurer = updatedAdventurers.get(0);

        assertEquals(20, adventurer.getXP());
    }

    @Test
    public void fightCriticalRollWithRogueAttackerLowersAttackedHpByTriple() throws Exception {
        List<Adventurer> rogues = generateRogues();
        when(roller.rollD20()).thenReturn(20);
        Adventurer attackingRogue = rogues.get(0);
        Adventurer attackedRogue = rogues.get(1);

        List<Adventurer> updatedAdventurers = adventurerService.fight(attackingRogue, attackedRogue);
        Adventurer damagedAdventurer = updatedAdventurers.get(1);

        assertEquals(2, damagedAdventurer.getHitPoints());
    }

    @Test
    public void attackingRogueIgnoresPositiveDexterityModifierToArmor() throws Exception {
        List<Adventurer> rogues = generateRogues();
        when(roller.rollD20()).thenReturn(10);
        Adventurer attackingRogue = rogues.get(0);
        Adventurer attackedRogue = rogues.get(1);
        attackedRogue.setDexterity(12);

        List<Adventurer> updatedAdventurers = adventurerService.fight(attackingRogue, attackedRogue);
        Adventurer damagedAdventurer = updatedAdventurers.get(1);

        assertEquals(4, damagedAdventurer.getHitPoints());
    }

    @Test
    public void attackingRogueKeepsNegativeDexterityModifierToArmor() throws Exception {
        List<Adventurer> rogues = generateRogues();
        when(roller.rollD20()).thenReturn(9);
        Adventurer attackingRogue = rogues.get(0);
        Adventurer attackedRogue = rogues.get(1);
        attackedRogue.setDexterity(9);
        attackedRogue.updateCharacter();

        List<Adventurer> updatedAdventurers = adventurerService.fight(attackingRogue, attackedRogue);
        Adventurer damagedAdventurer = updatedAdventurers.get(1);


        assertEquals(4, damagedAdventurer.getHitPoints());
    }

    public List<Adventurer> generateCharacters() throws Exception{
        Adventurer adventurer1 = adventurerBuilder
                .setId(1L)
                .setName("Cloud")
                .setAlignment(GOOD)
                .build();

        Adventurer adventurer2 = adventurerBuilder
                .setId(2L)
                .setName("Barret")
                .setAlignment(NEUTRAL)
                .build();

        Adventurer adventurer3 = adventurerBuilder
                .setId(3L)
                .setName("Sephiroth")
                .setAlignment(EVIL)
                .setHitPoints(0)
                .build();

        return List.of(adventurer1, adventurer2, adventurer3);
    }


    public List<Adventurer> generateFighters() {
        Fighter fightingCharacter1 = fighterBuilder
                .setId(11L)
                .setName("Maco")
                .setAlignment(GOOD)
                .build();

        Fighter fightingCharacter2 = fighterBuilder
                .setId(12L)
                .setName("Blaze")
                .setAlignment(NEUTRAL)
                .build();

        return List.of(fightingCharacter1,fightingCharacter2);
    }

    public List<Adventurer> generateRogues() throws Exception {
        Rogue rogueCharacter1 = rogueBuilder.
                setId(21l)
                .setName("Lily")
                .setAlignment(NEUTRAL)
                .build();

        Rogue rogueCharacter2 = rogueBuilder.
                setId(22l)
                .setName("John")
                .setAlignment(EVIL)
                .build();

        return List.of(rogueCharacter1,rogueCharacter2);
    }
}