package com.kmd.evercraft.character;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

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
    public void fightCharacters(@RequestBody List<Long> characterIDs) {

        adventurerService.fightCharacters(characterIDs.get(0),characterIDs.get(1));
    }

}
