package com.kmd.evercraft.character;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.stereotype.Service;

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
        Character character1 = new Character(1L,"Cloud","GOOD");
        Character character2 = new Character(2L, "Tifa","GOOD");

        when(characterRepository.findById(1L)).thenReturn(Optional.of(character1));
        when(characterRepository.findById(2L)).thenReturn(Optional.of(character2));

        characterService.fightCharacters(character1, character2);

        verify(characterRepository,times(1)).findById(eq(character1.getId()));
        verify(characterRepository, times(1)).findById(eq(character2.getId()));
    }

    @Test
    public void fightCharactersShouldSaveUpdatedCharacters() {
        Character character1 = new Character(1L,"Cloud","GOOD");
        Character character2 = new Character(2L, "Tifa","GOOD");

        characterService.fightCharacters(character1, character2);

        verify(characterRepository,times(1)).save(eq(character1));
        verify(characterRepository, times(1)).save(eq(character2));
    }

    @Test
    public void characterFightHigherRollShouldLowerAttackedHP() {
        Character character1 = new Character();
        Character character2 = new Character();

        int roll = 11;

        List<Character> updatedCharacters = characterService.fight(character1, character2, roll);

        Character updatedCharacter2 = updatedCharacters.get(1);

        assertEquals(4,updatedCharacter2.getHitPoints());

    }
}