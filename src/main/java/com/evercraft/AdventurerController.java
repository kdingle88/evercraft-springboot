package com.evercraft;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "api/v1/adventurers")
public class AdventurerController {

AdventurerService adventurerService;

    @Autowired
    public AdventurerController(AdventurerService adventurerService) {
        this.adventurerService = adventurerService;
    }

    public AdventurerController() {}

    @PostMapping
    public Adventurer createCharacter(@RequestBody Adventurer adventurer) {

        return null;
    }


}
