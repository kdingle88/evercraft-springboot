package com.kmd.evercraft.character;

public class RogueBuilder extends AdventurerBuilder implements Builder{
    private Rogue rogue;

    public RogueBuilder() {
        this.reset();
    }


    @Override
    public void reset() {
        this.rogue = new Rogue();
    }

    @Override
    public RogueBuilder setId(Long id) {
        this.rogue.id = id;
        return this;
    }

    @Override
    public RogueBuilder setName(String name) {
        this.rogue.name = name;
        return this;
    }

    @Override
    public RogueBuilder setAlignment(AdventurerAlignment alignment) throws Exception{
        this.rogue.alignment = alignment;

        if(alignment.equals(AdventurerAlignment.GOOD)) {
            throw new Exception("Good Alignment invalid for Rogue");
        }
        return this;
    }

    @Override
    public RogueBuilder setArmor(int armor) {
        this.rogue.armor = armor;
        return this;
    }

    @Override
    public RogueBuilder setHitPoints(int hitPoints) {
        this.rogue.hitPoints = hitPoints;
        return this;
    }

    @Override
    public RogueBuilder setStrength(int strength) {
        this.rogue.str = new Ability(strength);
        this.rogue.strength = strength;
        return this;
    }

    @Override
    public RogueBuilder setDexterity(int dexterity) {
        this.rogue.dex = new Ability(dexterity);
        this.rogue.dexterity = dexterity;
        return this;
    }

    @Override
    public RogueBuilder setWisdom(int wisdom) {
        this.rogue.wis = new Ability(wisdom);
        this.rogue.wisdom = wisdom;
        return this;
    }

    @Override
    public RogueBuilder setIntelligence(int intelligence) {
        this.rogue.intel = new Ability(intelligence);
        this.rogue.intelligence = intelligence;
        return this;
    }

    @Override
    public RogueBuilder setConstitution(int constitution) {
        this.rogue.con = new Ability(constitution);
        this.rogue.constitution = constitution;
        return this;
    }

    @Override
    public RogueBuilder setCharisma(int charisma) {
        this.rogue.cha = new Ability(charisma);
        this.rogue.charisma = charisma;
        return this;
    }

    @Override
    public RogueBuilder setXP(int xp) {

        this.rogue.xp = xp;
        return this;
    }

    @Override
    public Rogue build() {
        this.rogue.updateCharacter();
        Rogue createdRogue = this.rogue;
        this.reset();
        return createdRogue;
    }

}
