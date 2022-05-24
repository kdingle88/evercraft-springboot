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
    protected void addLevel(int xp) {

        int newLevel = (int) (xp / 1000) + 1;
        int baseHealthMultiplier = (int) (xp / 1000);

        if (getLevel() < newLevel) {

            this.level = newLevel;

            setHitPoints(getHitPoints() + (10 * baseHealthMultiplier + getModifier(getConstitution())));
        }
    }
    @Override
    public int getLevelMod() {
        return this.level;
    }
}
