package com.kmd.evercraft.character;

public class Monk extends Character{
    public Monk() {
        super();

        this.setHitPoints(6);
    }

    @Override
    protected void addLevel(int xp) {

        int newLevel = (int) (xp / 1000) + 1;
        int baseHealthMultiplier = (int) (xp / 1000);

        if (getLevel() < newLevel) {

            this.level = newLevel;

            setHitPoints(getHitPoints() + (6 * baseHealthMultiplier + getModifier(getConstitution())));
        }
    }
}
