package com.kmd.evercraft.character;

public class AdventurerBuilder implements Builder{
    private Adventurer adventurer;

    public AdventurerBuilder() {
        this.reset();
    }


    @Override
    public void reset() {
        this.adventurer = new Adventurer();
    }

    @Override
    public AdventurerBuilder setId(Long id) {
        this.adventurer.id = id;
        return this;
    }

    @Override
    public AdventurerBuilder setName(String name) {
        this.adventurer.name = name;
        return this;
    }

    @Override
    public AdventurerBuilder setAlignment(AdventurerAlignment alignment) throws Exception{
        this.adventurer.alignment = alignment;
        return this;
    }

    @Override
    public AdventurerBuilder setArmor(int armor) {
        this.adventurer.armor = armor;
        return this;
    }

    @Override
    public AdventurerBuilder setHitPoints(int hitPoints) {
        this.adventurer.hitPoints = hitPoints;
        return this;
    }

    @Override
    public AdventurerBuilder setStrength(int strength) {
        this.adventurer.str = new Ability(strength);
        this.adventurer.strength = strength;
        return this;
    }

    @Override
    public AdventurerBuilder setDexterity(int dexterity) {
        this.adventurer.dex = new Ability(dexterity);
        this.adventurer.dexterity = dexterity;
        return this;
    }

    @Override
    public AdventurerBuilder setWisdom(int wisdom) {
        this.adventurer.wis = new Ability(wisdom);
        this.adventurer.wisdom = wisdom;
        return this;
    }

    @Override
    public AdventurerBuilder setIntelligence(int intelligence) {
        this.adventurer.intel = new Ability(intelligence);
        this.adventurer.intelligence = intelligence;
        return this;
    }

    @Override
    public AdventurerBuilder setConstitution(int constitution) {
        this.adventurer.con = new Ability(constitution);
        this.adventurer.constitution = constitution;
        return this;
    }

    @Override
    public AdventurerBuilder setCharisma(int charisma) {
        this.adventurer.cha = new Ability(charisma);
        this.adventurer.charisma = charisma;
        return this;
    }

    @Override
    public AdventurerBuilder setXP(int xp) {

        this.adventurer.xp = xp;
        return this;
    }

    public Adventurer build() {
        this.adventurer.updateCharacter();
        Adventurer createdAdventurer = this.adventurer;
        this.reset();
        return createdAdventurer;
    }
}

