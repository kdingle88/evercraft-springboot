package com.kmd.evercraft.character;

public class Monk extends Adventurer {
    public Monk() {
        super();

        this.setHitPoints(6);
    }

    @Override
    public void updateHealth() {
        int modifiedHP = this.level * (6 + this.getCon().getModifier());

        setHitPoints(Math.max(modifiedHP, 1));
    }

    @Override
    public void updateArmor() {
        int armorMod = getDex().getModifier();

        if(getWis().getModifier() > 0) {
            armorMod += getWis().getModifier();
        }

        setArmor(getArmor() + armorMod);
    }
}
