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

    public List<Character> generateCharacters() {
        Character character1 = new Character(1L,"Cloud","GOOD");
        Character character2 = new Character(2L, "Tifa","GOOD");


        return List.of(character1,character2);
    }
}