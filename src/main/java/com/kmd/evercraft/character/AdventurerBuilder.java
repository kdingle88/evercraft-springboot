package com.kmd.evercraft.character;

public class AdventurerBuilder implements Builder{
    private Adventurer adventurer;

    public AdventurerBuilder(Adventurer adventurer) {
        this.adventurer = adventurer;
    }


    @Override
    public void reset() {
        this.adventurer = new Adventurer();
    }

    @Override
    public void setName(String name) {
        this.adventurer.name = name;
    }

    @Override
    public void setAlignment(AdventurerAlignment alignment) {
        this.adventurer.alignment = alignment;
    }

    @Override
    public void setArmor(int armor) {
        this.adventurer.armor = armor;
    }

    @Override
    public void setHitPoints(int hitPoints) {
        this.adventurer.hitPoints = hitPoints;
    }

    @Override
    public void setStrength(int strength) {
        this.adventurer.str = new Ability(strength);
        this.adventurer.strength = strength;
    }

    @Override
    public void setDexterity(int dexterity) {
        this.adventurer.dex = new Ability(dexterity);
        this.adventurer.dexterity = dexterity;
    }

    @Override
    public void setWisdom(int wisdom) {
        this.adventurer.wis = new Ability(wisdom);
        this.adventurer.wisdom = wisdom;
    }

    @Override
    public void setIntelligence(int intelligence) {
        this.adventurer.intel = new Ability(intelligence);
        this.adventurer.intelligence = intelligence;
    }

    @Override
    public void setConstitution(int constitution) {
        this.adventurer.con = new Ability(constitution);
        this.adventurer.constitution = constitution;
    }

    @Override
    public void setCharisma(int charisma) {
        this.adventurer.cha = new Ability(charisma);
        this.adventurer.charisma = charisma;
    }

    @Override
    public void setXP(int xp) {
        this.adventurer.xp = xp;
    }
}
