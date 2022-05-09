package com.kmd.evercraft.character;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CharacterService {

    @Autowired
    CharacterRepository characterRepository;

    public Character addCharacter(Character character) {

        return characterRepository.save(character);
    }
}
