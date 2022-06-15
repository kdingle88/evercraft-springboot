package com.kmd.evercraft.character;

import com.kmd.evercraft.DiceRoller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

@Service
public class AdventurerService {

    @Autowired
    AdventurerRepository adventurerRepository;


    DiceRoller roller = new DiceRoller(new Random());

    public Adventurer addCharacter(Adventurer adventurer) {

        return adventurerRepository.save(adventurer);

    }

    public void fightCharacters(Long firstCharacterID, Long secondCharacterID) {
        Adventurer attackingAdventurer = adventurerRepository.findById(firstCharacterID)
                .orElseThrow(() -> new IllegalStateException("Character with id " + firstCharacterID + " does not exist." ));

        Adventurer attackedAdventurer = adventurerRepository.findById(secondCharacterID)
                .orElseThrow(() -> new IllegalStateException("Character with id " + secondCharacterID + " does not exist." ));

        List<Adventurer> updatedAdventurers = fight(attackingAdventurer, attackedAdventurer);

        if(updatedAdventurers.get(1).isCharacterDead()) {
            adventurerRepository.delete(updatedAdventurers.get(1));
            updatedAdventurers.remove(1);
        }

        updatedAdventurers.stream().forEach(character -> adventurerRepository.save(character));

    }

    public List<Adventurer> fight(Adventurer attackingAdventurer, Adventurer attackedAdventurer) {

        int naturalRoll = roller.rollD20();
        int totalRoll = attackingAdventurer.getTotalRoll(naturalRoll);



        boolean isHit = isHit(attackingAdventurer, attackedAdventurer, totalRoll);
        boolean isCriticalHit = naturalRoll == 20;

        int damage = 0;


        if(isHit) {
            damage += attackingAdventurer.calculateDamage();
            attackingAdventurer.addXP(10);
        }

        if(isCriticalHit) {
            damage += (attackingAdventurer.calculateDamage() * attackingAdventurer.getCritModifier());
        }

        attackedAdventurer.takeDamage(damage);

        return new ArrayList<>(Arrays.asList(attackingAdventurer, attackedAdventurer));
    }

    private boolean isHit(Adventurer attackingAdventurer, Adventurer attackedAdventurer, int totalRoll) {
        int attackedArmor = attackedAdventurer.getArmor();

        if(attackingAdventurer.canIgnoreDex() && attackedAdventurer.getDexterity() > 10) {
            attackedArmor -= attackedAdventurer.getDex().getModifier();
        }
        return attackedArmor <= totalRoll;
    }

    public List<Adventurer> getCharacters() {
        return adventurerRepository.findAll();
    }
}
