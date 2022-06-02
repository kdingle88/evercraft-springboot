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

    @Override
    public int getTotalRoll(int naturalRoll) {
        int dexMod = getModifier(getDexterity());
        int levelMod = getLevelMod();

        return naturalRoll + dexMod + levelMod;
    }

    @Override
    public int calculateDamage() {
        int damage = 1;
        int modifier = getModifier(getDexterity());

        if(modifier > 0) {
            damage += modifier;
        }
        return damage;
    }
}


