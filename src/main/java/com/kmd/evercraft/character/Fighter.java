package com.kmd.evercraft.character;

public class Fighter extends Adventurer {


    public Fighter() {
        super();

        this.setHitPoints(10);

    }

    public Fighter(Long id, String name, AdventurerAlignment alignment) {
        super(id, name, alignment);

        this.setHitPoints(10);
    }

    public Fighter(String name, AdventurerAlignment alignment) {
        super(name, alignment);

        this.setHitPoints(10);
    }


    @Override
    public int getLevelMod() {
        return this.level;
    }


    @Override
    public void updateHealth() {
        int modifiedHP = this.level * (10 + this.con.getModifier());

        setHitPoints(Math.max(modifiedHP, 1));
    }
}
