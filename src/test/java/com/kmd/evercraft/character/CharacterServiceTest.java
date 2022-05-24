package com.kmd.evercraft.character;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static com.kmd.evercraft.character.CharacterAlignment.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CharacterServiceTest {

    @InjectMocks
    @Spy
    CharacterService characterService = new CharacterService();

    @Mock
    CharacterRepository characterRepository;

    @Test
    public void fightCharactersFindsCharacters() {
        List<Character> characters = generateCharacters();

        when(characterRepository.findById(1L)).thenReturn(Optional.of(characters.get(0)));
        when(characterRepository.findById(2L)).thenReturn(Optional.of(characters.get(1)));

        characterService.fightCharacters(characters.get(0), characters.get(1));

        verify(characterRepository,times(1)).findById(eq(characters.get(0).getId()));
        verify(characterRepository, times(1)).findById(eq(characters.get(1).getId()));
    }

    @Test
    public void fightCharactersSavesUpdatedCharacters() {
        List<Character> characters = generateCharacters();

        when(characterRepository.findById(1L)).thenReturn(Optional.of(characters.get(0)));
        when(characterRepository.findById(2L)).thenReturn(Optional.of(characters.get(1)));

        characterService.fightCharacters(characters.get(0), characters.get(1));

        verify(characterRepository,times(1)).save(eq(characters.get(0)));
        verify(characterRepository, times(1)).save(eq(characters.get(1)));
    }
    @Test
    public void fightCharactersDeletesCharactersWhenHPIsZero() {
        List<Character> characters = generateCharacters();

        when(characterRepository.findById(1L)).thenReturn(Optional.of(characters.get(0)));
        when(characterRepository.findById(3L)).thenReturn(Optional.of(characters.get(2)));

        characterService.fightCharacters(characters.get(0), characters.get(2));

        verify(characterRepository,times(1)).delete(eq(characters.get(2)));

        verify(characterRepository,times(1)).save(eq(characters.get(0)));
        verify(characterRepository,times(0)).save(eq(characters.get(2)));
    }

    @Test
    public void fightHigherRollThanAttackedArmorLowersHP() {
        List<Character> characters = generateCharacters();

        Character attackingCharacter = characters.get(0);
        Character attackedCharacter = characters.get(1);

        doReturn(11).when(characterService).getNaturalRoll();

        List<Character> updatedCharacters = characterService.fight(attackingCharacter, attackedCharacter);

        Character damagedCharacter = updatedCharacters.get(1);

        assertEquals(4,damagedCharacter.getHitPoints());
    }

    @Test
    public void fightEqualRollToAttackedArmorLowersHP() {
        List<Character> characters = generateCharacters();

        Character attackingCharacter = characters.get(0);
        Character attackedCharacter = characters.get(1);

        doReturn(10).when(characterService).getNaturalRoll();

        List<Character> updatedCharacters = characterService.fight(attackingCharacter, attackedCharacter);

        Character damagedCharacter = updatedCharacters.get(1);

        assertEquals(4,damagedCharacter.getHitPoints());

    }

    @Test
    public void fightLowerRollThanAttackedArmorKeepsHPTheSame() {
        List<Character> characters = generateCharacters();

        Character attackingCharacter = characters.get(0);
        Character attackedCharacter = characters.get(1);

        doReturn(9).when(characterService).getNaturalRoll();

        List<Character> updatedCharacters = characterService.fight(attackingCharacter, attackedCharacter);

        Character notDamagedCharacter = updatedCharacters.get(1);

        assertEquals(5,notDamagedCharacter.getHitPoints());

    }

    @Test
    public void fightCriticalRollLowersAttackedHpByDouble() {
        List<Character> characters = generateCharacters();

        Character attackingCharacter = characters.get(0);
        Character attackedCharacter = characters.get(1);

        doReturn(20).when(characterService).getNaturalRoll();

        List<Character> updatedCharacters = characterService.fight(attackingCharacter, attackedCharacter);

        Character damagedCharacter = updatedCharacters.get(1);

        assertEquals(3,damagedCharacter.getHitPoints());
    }

    @Test
    public void attackingStrengthModifierIncreasesTotalRoll() {
        List<Character> characters = generateCharacters();

        Character attackingCharacter = characters.get(0);

        attackingCharacter.setStrength(12);

        int roll = 9;
        int rollWithModifer = attackingCharacter.getTotalRoll(roll);

        assertEquals(10,rollWithModifer);
    }

    @Test
    public void minimumDamageIsOneOnCritical() {
        List<Character> characters = generateCharacters();

        Character attackingCharacter = characters.get(0);
        Character attackedCharacter = characters.get(1);

        attackingCharacter.setStrength(1);
        attackedCharacter.setArmor(21);

        doReturn(20).when(characterService).getNaturalRoll();

        List<Character> updatedCharacters = characterService.fight(attackingCharacter, attackedCharacter);

        Character damagedCharacter = updatedCharacters.get(1);

        assertEquals(4,damagedCharacter.getHitPoints());
    }

    @Test
    public void attackIncreaseWithEvenLevel() {
        List<Character> characters = generateCharacters();

        Character attackingCharacter = characters.get(0);
        Character attackedCharacter = characters.get(1);

        attackingCharacter.setXP(3000);

        doReturn(9).when(characterService).getNaturalRoll();

        List<Character> updatedCharacters = characterService.fight(attackingCharacter, attackedCharacter);

        Character damagedCharacter = updatedCharacters.get(1);

        assertEquals(4,damagedCharacter.getHitPoints());

    }
    @Test
    public void attackStaysWithOddLevel() {
        List<Character> characters = generateCharacters();

        Character attackingCharacter = characters.get(0);
        Character attackedCharacter = characters.get(1);

        attackingCharacter.setXP(2000);

        doReturn(8).when(characterService).getNaturalRoll();

        List<Character> updatedCharacters = characterService.fight(attackingCharacter, attackedCharacter);

        Character damagedCharacter = updatedCharacters.get(1);

        assertEquals(5,damagedCharacter.getHitPoints());

    }
    @Test
    public void fighterAttackIncreaseEveryLevelIncrease() {
        List<Character> fighters = generateFighters();

        Character attacking = fighters.get(0);
        Character attacked = fighters.get(1);

        attacking.setXP(2000);

        doReturn(8).when(characterService).getNaturalRoll();

        System.out.println(attacking.getXP());
        System.out.println(attacking.getLevel());
        System.out.println(attacking.getHitPoints());
        List<Character> updatedCharacters = characterService.fight(attacking, attacked);


        Character damaged = updatedCharacters.get(1);

        assertEquals(9,damaged.getHitPoints());

    }

    @Test
    public void onHitAttackerGainsTenXP() {
        List<Character> characters = generateCharacters();

        Character attackingCharacter = characters.get(0);
        Character attackedCharacter = characters.get(1);

        doReturn(11).when(characterService).getNaturalRoll();

        List<Character> updatedCharacters = characterService.fight(attackingCharacter, attackedCharacter);

        Character character = updatedCharacters.get(0);

        assertEquals(10,character.getXP());
    }

    @Test
    public void onMissAttackerXPStaysTheSame() {
        List<Character> characters = generateCharacters();

        Character attackingCharacter = characters.get(0);
        Character attackedCharacter = characters.get(1);

        doReturn(9).when(characterService).getNaturalRoll();

        List<Character> updatedCharacters = characterService.fight(attackingCharacter, attackedCharacter);

        Character character = updatedCharacters.get(0);

        assertEquals(0,character.getXP());
    }

    @Test
    public void attackerCanLevelUpWhenGainingXP() {
        List<Character> characters = generateCharacters();

        Character attackingCharacter = characters.get(0);
        Character attackedCharacter = characters.get(1);

        attackingCharacter.setXP(990);

        doReturn(11).when(characterService).getNaturalRoll();

        List<Character> updatedCharacters = characterService.fight(attackingCharacter, attackedCharacter);

        Character character = updatedCharacters.get(0);

        assertEquals(1000,character.getXP());
        assertEquals(2,character.getLevel());
    }

    @Test
    public void onTwoHitsAttackerXPIncreasesTwenty() {
        List<Character> characters = generateCharacters();

        Character attackingCharacter = characters.get(0);
        Character attackedCharacter = characters.get(1);

        doReturn(11).when(characterService).getNaturalRoll();

        List<Character> updatedCharacters = characterService.fight(attackingCharacter, attackedCharacter);
        updatedCharacters = characterService.fight(updatedCharacters.get(0), updatedCharacters.get(1));

        Character character = updatedCharacters.get(0);

        assertEquals(20,character.getXP());
    }

    public List<Character> generateCharacters() {
        Character character1 = new Character(1L,"Cloud",GOOD);
        Character character2 = new Character(2L, "Barret",NEUTRAL);
        Character dyingCharacter = new Character(3L, "Sephiroth",EVIL);

        dyingCharacter.setHitPoints(0);

        return List.of(character1,character2, dyingCharacter);
    }

    public List<Character> generateFighters() {
        Fighter fightingCharacter1 = new Fighter(11L,"Maco",GOOD);
        Fighter fightingCharacter2 = new Fighter(12L,"Blaze",NEUTRAL);

        return List.of(fightingCharacter1,fightingCharacter2);

    }
}