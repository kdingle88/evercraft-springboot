package com.kmd.evercraft.character;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "api/v1/characters")
public class AdventurerController {

    private final AdventurerService adventurerService;

    @Autowired
    public AdventurerController(AdventurerService adventurerService) {
        this.adventurerService = adventurerService;
    }

    @PostMapping
    public Adventurer createCharacter(@RequestBody Adventurer adventurer) {

        return adventurerService.addCharacter(adventurer);
    }

    @PutMapping(path = "/fight")
    public void fightCharacters(@RequestParam Long attackingCharacterID, @RequestParam Long attackedCharacterID) {

        adventurerService.fightCharacters(attackingCharacterID,attackedCharacterID);
    }

}
