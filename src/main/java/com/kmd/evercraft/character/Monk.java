package com.kmd.evercraft.character;

public class Monk extends Adventurer {
    public Monk() {
        super();

        this.setHitPoints(6);
    }

    @Override
    public void updateHealth() {
        int modifiedHP = this.level * (6 + this.con.getModifier());

        setHitPoints(Math.max(modifiedHP, 1));
    }

    @Override
    public void updateArmor() {
        int armorMod = this.dex.getModifier();

        if(this.wis.getModifier() > 0) {
            armorMod += this.wis.getModifier();
        }

        setArmor(getArmor() + armorMod);
    }
}
