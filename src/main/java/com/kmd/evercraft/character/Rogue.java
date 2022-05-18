package com.kmd.evercraft.character;

public class Rogue extends Character{

    public Rogue(Long id, String name, CharacterAlignment alignment) throws Exception {
        super(id, name, alignment);

        if(alignment.equals(CharacterAlignment.GOOD)) {
            throw new Exception("Good Alignment invalid for Rogue");
        }

    }

    public Rogue(String name, CharacterAlignment alignment) {
        super(name, alignment);
    }
}