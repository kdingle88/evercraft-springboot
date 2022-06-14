package com.kmd.evercraft.character;

public class MonkBuilder extends AdventurerBuilder implements Builder{
    private Monk Monk;

    public MonkBuilder() {this.reset();}

    @Override
    public void reset() {
        this.Monk = new Monk();
    }

    @Override
    public MonkBuilder setId(Long id) {
        this.Monk.id = id;
        return this;
    }

    @Override
    public MonkBuilder setName(String name) {
        this.Monk.name = name;
        return this;
    }

    @Override
    public MonkBuilder setAlignment(AdventurerAlignment alignment) {
        this.Monk.alignment = alignment;
        return this;
    }

    @Override
    public MonkBuilder setArmor(int armor) {
        this.Monk.armor = armor;
        return this;
    }

    @Override
    public MonkBuilder setHitPoints(int hitPoints) {
        this.Monk.hitPoints = hitPoints;
        return this;
    }

    @Override
    public MonkBuilder setStrength(int strength) {
        this.Monk.str = new Ability(strength);
        this.Monk.strength = strength;
        return this;
    }

    @Override
    public MonkBuilder setDexterity(int dexterity) {
        this.Monk.dex = new Ability(dexterity);
        this.Monk.dexterity = dexterity;
        return this;
    }

    @Override
    public MonkBuilder setWisdom(int wisdom) {
        this.Monk.wis = new Ability(wisdom);
        this.Monk.wisdom = wisdom;
        return this;
    }

    @Override
    public MonkBuilder setIntelligence(int intelligence) {
        this.Monk.intel = new Ability(intelligence);
        this.Monk.intelligence = intelligence;
        return this;
    }

    @Override
    public MonkBuilder setConstitution(int constitution) {
        this.Monk.con = new Ability(constitution);
        this.Monk.constitution = constitution;
        return this;
    }

    @Override
    public MonkBuilder setCharisma(int charisma) {
        this.Monk.cha = new Ability(charisma);
        this.Monk.charisma = charisma;
        return this;
    }

    @Override
    public MonkBuilder setXP(int xp) {

        this.Monk.xp = xp;
        return this;
    }

    @Override
    public Monk build() {
        this.Monk.updateCharacter();
        Monk createdMonk = this.Monk;
        this.reset();
        return createdMonk;
    }
}
