package com.kmd.evercraft.character;

import com.kmd.evercraft.character.Character;

public class Fighter extends Character {

    public Fighter() {
        super();

        this.setHitPoints(10);

    }

    public Fighter(Long id, String name, CharacterAlignment alignment) {
        super(id, name, alignment);

        this.setHitPoints(10);
    }

    public Fighter(String name, CharacterAlignment alignment) {
        super(name, alignment);

        this.setHitPoints(10);
    }


    @Override
    protected void setLevel(int xp) {

        int newLevel = (int) (xp / 1000) + 1;

        if (getLevel() < newLevel) {
            setLevel(newLevel);

            setHitPoints(getHitPoints() + (10 + getModifier(getConstitution())));
        }
    }
}
