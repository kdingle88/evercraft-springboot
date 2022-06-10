package com.kmd.evercraft.character;

public class Rogue extends Adventurer {
    private int critModifier = 2;
    private boolean ignoreDex = true;

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
    public int getCritModifier() { return this.critModifier;}
    @Override
    public boolean canIgnoreDex() { return this.ignoreDex; }
    @Override
    public int getTotalRoll(int naturalRoll) {
        int dexMod = this.dex.getModifier();
        int levelMod = this.getLevelMod();

        return naturalRoll + dexMod + levelMod;
    }

    @Override
    public int calculateDamage() {
        int damage = 1;
        int modifier = this.dex.getModifier();

        if(modifier > 0) {
            damage += modifier;
        }
        return damage;
    }
}


