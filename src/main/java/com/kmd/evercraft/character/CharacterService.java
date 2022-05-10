package com.kmd.evercraft.character;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

        int roll = 11;

        List<Character> updatedCharacters = fight(attackingCharacter,attackedCharacter,roll);

        updatedCharacters.stream().forEach(character -> characterRepository.save(character));

    }

    public List<Character> fight(Character attackingCharacter, Character attackedCharacter, int roll) {

        boolean isHit = attackingCharacter.attack(attackedCharacter,roll);

        if(isHit) {
            int currentHp = attackedCharacter.getHitPoints();
            int rollArmorDifference = roll - attackedCharacter.getArmor();

            attackedCharacter.setHitPoints(currentHp - rollArmorDifference);
        }

        return List.of(attackingCharacter,attackedCharacter);

    }

}
