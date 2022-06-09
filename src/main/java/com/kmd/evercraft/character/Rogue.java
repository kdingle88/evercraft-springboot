package com.kmd.evercraft.character;

public class Rogue extends Adventurer {

    public Rogue(Long id, String name, AdventurerAlignment alignment) throws Exception {
        super(id, name, alignment);

        if(alignment.equals(AdventurerAlignment.GOOD)) {
            throw new Exception("Good Alignment invalid for Rogue");
        }

    }

    public Rogue(String name, AdventurerAlignment alignment) {
        super(name, alignment);
    }

    @Override
    public int getTotalRoll(int naturalRoll) {
        int dexMod = this.getDex().getModifier();
        int levelMod = getLevelMod();

        return naturalRoll + dexMod + levelMod;
    }

    @Override
    public int calculateDamage() {
        int damage = 1;
        int modifier = this.getDex().getModifier();

        if(modifier > 0) {
            damage += modifier;
        }
        return damage;
    }
}


