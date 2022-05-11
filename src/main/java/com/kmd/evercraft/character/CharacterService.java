package com.kmd.evercraft.character;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class CharacterService {

    @Autowired
    CharacterRepository characterRepository;

    public Character addCharacter(Character character) {

        return characterRepository.save(character);

    }

    public void fightCharacters(Character character1, Character character2) {
        Character attackingCharacter = characterRepository.findById(character1.getId())
                .orElseThrow(() -> new IllegalStateException("Character with id " + character1.getId() + " does not exist." ));

        Character attackedCharacter = characterRepository.findById(character2.getId())
                .orElseThrow(() -> new IllegalStateException("Character with id " + character2.getId() + " does not exist." ));

        int roll = (int)(Math.random() * (20)) + 1 + attackingCharacter.getModifier(attackingCharacter.getStrength());

        List<Character> updatedCharacters = fight(attackingCharacter,attackedCharacter,roll);

        if(isCharacterDead(updatedCharacters.get(1))) {
            characterRepository.delete(updatedCharacters.get(1));
            updatedCharacters.remove(1);
        }

        updatedCharacters.stream().forEach(character -> characterRepository.save(character));

    }

    public List<Character> fight(Character attackingCharacter, Character attackedCharacter, int roll) {

        boolean isHit = attackingCharacter.attack(attackedCharacter,roll);
        boolean isCriticalHit = roll == 20;

        if(isCriticalHit) {
            attackedCharacter.setHitPoints(attackedCharacter.getHitPoints() - 2);

            return List.of(attackingCharacter,attackedCharacter);
        }

        if(isHit) {
            attackedCharacter.setHitPoints(attackedCharacter.getHitPoints() - 1 );
            attackedCharacter.setHitPoints(attackedCharacter.getHitPoints() - attackingCharacter.getModifier(attackingCharacter.getStrength()));
            return List.of(attackingCharacter,attackedCharacter);
        }

        return new ArrayList<>(Arrays.asList(attackingCharacter,attackedCharacter));

    }

    public boolean isCharacterDead(Character character) {
        return character.getHitPoints() <= 0;
    }
}
