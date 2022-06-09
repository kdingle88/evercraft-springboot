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
    protected void addLevel(int xp) {

        int newLevel = (xp / 1000) + 1;
        int baseHealthMultiplier = (xp / 1000);

        if (getLevel() < newLevel) {

            this.level = newLevel;

            setHitPoints(getHitPoints() + (10 * baseHealthMultiplier + this.getCon().getModifier()));
        }
    }
    @Override
    public int getLevelMod() {
        return this.level;
    }
}
