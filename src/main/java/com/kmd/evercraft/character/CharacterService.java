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

    public void fightCharacters(Long firstCharacterID, Long secondCharacterID) {
        Character attackingCharacter = characterRepository.findById(firstCharacterID)
                .orElseThrow(() -> new IllegalStateException("Character with id " + firstCharacterID + " does not exist." ));

        Character attackedCharacter = characterRepository.findById(secondCharacterID)
                .orElseThrow(() -> new IllegalStateException("Character with id " + secondCharacterID + " does not exist." ));

        List<Character> updatedCharacters = fight(attackingCharacter,attackedCharacter);

        if(updatedCharacters.get(1).isCharacterDead()) {
            characterRepository.delete(updatedCharacters.get(1));
            updatedCharacters.remove(1);
        }

        updatedCharacters.stream().forEach(character -> characterRepository.save(character));

    }

    public List<Character> fight(Character attackingCharacter, Character attackedCharacter) {

        int naturalRoll = getNaturalRoll();
        int totalRoll = attackingCharacter.getTotalRoll(naturalRoll);

        boolean isHit = attackingCharacter.attack(attackedCharacter,totalRoll);
        boolean isCriticalHit = naturalRoll == 20;

        int damage = 0;

        if(isHit) {
            damage += attackingCharacter.calculateDamage();
            attackingCharacter.addXP(10);
        }

        if(isCriticalHit) {
            damage += attackingCharacter.calculateDamage();
        }

        attackedCharacter.setHitPoints(attackedCharacter.getHitPoints() - damage);


        return new ArrayList<>(Arrays.asList(attackingCharacter,attackedCharacter));

    }

    int getNaturalRoll() {
        return (int) (Math.random() * (20)) + 1;
    }

}
