package com.kmd.evercraft.character;

import org.junit.jupiter.api.Test;
import org.springframework.stereotype.Service;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@Service
class CharacterServiceTest {

    CharacterService characterService = new CharacterService();

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