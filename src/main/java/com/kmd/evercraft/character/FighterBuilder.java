package com.kmd.evercraft.character;

public class FighterBuilder extends AdventurerBuilder implements Builder{
    private Fighter fighter;

    public FighterBuilder() {
        this.reset();
    }


    @Override
    public void reset() {
        this.fighter = new Fighter();
    }

    @Override
    public FighterBuilder setId(Long id) {
        this.fighter.id = id;
        return this;
    }

    @Override
    public FighterBuilder setName(String name) {
        this.fighter.name = name;
        return this;
    }

    @Override
    public FighterBuilder setAlignment(AdventurerAlignment alignment) {
        this.fighter.alignment = alignment;
        return this;
    }

    @Override
    public FighterBuilder setArmor(int armor) {
        this.fighter.armor = armor;
        return this;
    }

    @Override
    public FighterBuilder setHitPoints(int hitPoints) {
        this.fighter.hitPoints = hitPoints;
        return this;
    }

    @Override
    public FighterBuilder setStrength(int strength) {
        this.fighter.str = new Ability(strength);
        this.fighter.strength = strength;
        return this;
    }

    @Override
    public FighterBuilder setDexterity(int dexterity) {
        this.fighter.dex = new Ability(dexterity);
        this.fighter.dexterity = dexterity;
        return this;
    }

    @Override
    public FighterBuilder setWisdom(int wisdom) {
        this.fighter.wis = new Ability(wisdom);
        this.fighter.wisdom = wisdom;
        return this;
    }

    @Override
    public FighterBuilder setIntelligence(int intelligence) {
        this.fighter.intel = new Ability(intelligence);
        this.fighter.intelligence = intelligence;
        return this;
    }

    @Override
    public FighterBuilder setConstitution(int constitution) {
        this.fighter.con = new Ability(constitution);
        this.fighter.constitution = constitution;
        return this;
    }

    @Override
    public FighterBuilder setCharisma(int charisma) {
        this.fighter.cha = new Ability(charisma);
        this.fighter.charisma = charisma;
        return this;
    }

    @Override
    public FighterBuilder setXP(int xp) {

        this.fighter.xp = xp;
        return this;
    }

    @Override
    public Fighter build() {
        this.fighter.updateCharacter();
        Fighter createdFighter = this.fighter;
        this.reset();
        return createdFighter;
    }
}
