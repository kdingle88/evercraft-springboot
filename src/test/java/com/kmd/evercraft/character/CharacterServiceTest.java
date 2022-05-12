package com.kmd.evercraft.character;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CharacterServiceTest {

    @InjectMocks
    CharacterService characterService = new CharacterService();

    @Mock
    CharacterRepository characterRepository;

    @Test
    public void fightCharactersShouldFindCharacters() {
        List<Character> characters = generateCharacters();

        when(characterRepository.findById(1L)).thenReturn(Optional.of(characters.get(0)));
        when(characterRepository.findById(2L)).thenReturn(Optional.of(characters.get(1)));

        characterService.fightCharacters(characters.get(0), characters.get(1));

        verify(characterRepository,times(1)).findById(eq(characters.get(0).getId()));
        verify(characterRepository, times(1)).findById(eq(characters.get(1).getId()));
    }

    @Test
    public void fightCharactersShouldSaveUpdatedCharacters() {
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

        int roll = 11;

        List<Character> updatedCharacters = characterService.fight(characters.get(0), characters.get(1), roll);

        Character updatedCharacter2 = updatedCharacters.get(1);

        assertEquals(4,updatedCharacter2.getHitPoints());

    }

    @Test
    public void fightEqualRollToAttackedArmorLowersHP() {
        List<Character> characters = generateCharacters();

        int roll = 10;

        List<Character> updatedCharacters = characterService.fight(characters.get(0), characters.get(1), roll);

        Character updatedCharacter2 = updatedCharacters.get(1);

        assertEquals(4,updatedCharacter2.getHitPoints());

    }

    @Test
    public void fightLowerRollThanAttackedArmorKeepsHPTheSame() {
        List<Character> characters = generateCharacters();

        int roll = 9;

        List<Character> updatedCharacters = characterService.fight(characters.get(0), characters.get(1), roll);

        Character updatedCharacter2 = updatedCharacters.get(1);

        assertEquals(5,updatedCharacter2.getHitPoints());

    }

    @Test
    public void fightCriticalRollLowersAttackedHpByDouble() {
        List<Character> characters = generateCharacters();

        int roll = 20;

        List<Character> updatedCharacters = characterService.fight(characters.get(0), characters.get(1), roll);

        Character updatedCharacter2 = updatedCharacters.get(1);

        assertEquals(3,updatedCharacter2.getHitPoints());
    }

    @Test
    public void attackingStrengthModifierIncreasesRollAndDamageDealt() {
        List<Character> characters = generateCharacters();

        Character attackingCharacter = characters.get(0);
        Character attackedCharacter = characters.get(1);

        attackingCharacter.setStrength(12);

        int roll = 9 + attackingCharacter.getModifier(attackingCharacter.getStrength());

        List<Character> updatedCharacters = characterService.fight(attackingCharacter, attackedCharacter, roll);

        Character updatedAttackedCharacter = updatedCharacters.get(1);

        assertEquals(3,updatedAttackedCharacter.getHitPoints());
    }

    @Test
    public void attackingStrengthModifierDoubledWithCritical() {
        List<Character> characters = generateCharacters();

        Character attackingCharacter = characters.get(0);
        Character attackedCharacter = characters.get(1);

        attackingCharacter.setStrength(12);

        int naturalRoll = 20;

        int roll = naturalRoll + attackingCharacter.getModifier(attackingCharacter.getStrength());

        List<Character> updatedCharacters = characterService.fight(attackingCharacter, attackedCharacter, roll);

        Character updatedAttackedCharacter = updatedCharacters.get(1);

        assertEquals(1,updatedAttackedCharacter.getHitPoints());
    }

    @Test
    public void minimumDamageIsOne() {
        List<Character> characters = generateCharacters();

        Character attackingCharacter = characters.get(0);
        Character attackedCharacter = characters.get(1);

        attackingCharacter.setStrength(1);

        int roll = 20;

        List<Character> updatedCharacters = characterService.fight(attackingCharacter, attackedCharacter, roll);

        Character updatedAttackedCharacter = updatedCharacters.get(1);

        assertEquals(4,updatedAttackedCharacter.getHitPoints());

    }


    public List<Character> generateCharacters() {
        Character character1 = new Character(1L,"Cloud","GOOD");
        Character character2 = new Character(2L, "Tifa","GOOD");
        Character dyingCharacter = new Character(3L, "Barret","NEUTRAL");

        dyingCharacter.setHitPoints(0);

        return List.of(character1,character2, dyingCharacter);
    }
}