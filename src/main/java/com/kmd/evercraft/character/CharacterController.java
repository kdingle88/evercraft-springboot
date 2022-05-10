package com.kmd.evercraft.character;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/characters")
public class CharacterController {

    private final CharacterService characterService;

    @Autowired
    public CharacterController(CharacterService characterService) {
        this.characterService = characterService;
    }

    @PostMapping
    public Character createCharacter(@RequestBody Character character) {

        return characterService.addCharacter(character);
    }

    @PutMapping(path = "/fight")
    public void fightCharacters(@RequestBody List<Character> fightingCharacters) {

    }

}
