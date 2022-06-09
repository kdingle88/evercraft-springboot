package com.kmd.evercraft.character;

public class Monk extends Adventurer {
    private int wisdom = 10;
    private Ability wis = new Ability();

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

            setHitPoints(getHitPoints() + (6 * baseHealthMultiplier + this.getCon().getModifier()));
        }
    }

    @Override
    public void setWisdom(int wisdom) {
        this.wisdom = wisdom;
        this.wis = new Ability(wisdom);
        if(this.wis.getModifier() > 0) {
            setArmor(getArmor() + this.wis.getModifier());
        }

    }
}
