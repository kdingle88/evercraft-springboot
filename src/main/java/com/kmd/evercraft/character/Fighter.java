package com.kmd.evercraft.character;

import com.kmd.evercraft.character.Character;

public class Fighter extends Character {

    public Fighter() {
        super();
    }

    public Fighter(Long id, String name, CharacterAlignment alignment) {
        super(id, name, alignment);
    }

    public Fighter(String name, CharacterAlignment alignment) {
        super(name, alignment);
    }
}
