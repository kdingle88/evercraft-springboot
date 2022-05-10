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
        characterRepository.save(character1);
        characterRepository.save(character2);

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
