package com.kmd.evercraft.character;

import javax.persistence.*;

@Entity
public class Adventurer {
    @SequenceGenerator(name = "character_sequence",sequenceName = "character_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "character_sequence")
    @Id
    private Long id;
    private String name;
    private AdventurerAlignment alignment;

    private int armor = 10;

    private int hitPoints = 5;

    private int strength = 10;
    @Transient
    protected Ability str = new Ability();

    private int dexterity = 10;
    @Transient
    protected Ability dex = new Ability();

    private int constitution = 10;
    @Transient
    protected Ability con = new Ability();

    private int wisdom = 10;
    @Transient
    protected Ability wis = new Ability();

    private int intelligence = 10;
    @Transient
    protected Ability intel = new Ability();

    private int charisma = 10;
    @Transient
    protected Ability cha = new Ability();

    protected int level = 1;
    private int xp;
    @Transient
    protected int critModifier = 1;
    @Transient
    protected boolean IgnoreDex = false;



    public Adventurer() {
    }

    public Adventurer(Long id, String name, AdventurerAlignment alignment) {
        this.id = id;
        this.name = name;
        this.alignment = alignment;
    }

    public Adventurer(String name, AdventurerAlignment alignment) {
        this.name = name;
        this.alignment = alignment;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public AdventurerAlignment getAlignment() {
        return alignment;
    }

    public void setAlignment(AdventurerAlignment alignment) {
        this.alignment = alignment;
    }

    public int getArmor() {

        return armor;
    }

    public void setArmor(int armor) {
        this.armor = armor;
    }

    public int getHitPoints() {
        return hitPoints;
    }

    public void setHitPoints(int hitPoints) {
        this.hitPoints = hitPoints;
    }

    public int getStrength() {
        return this.strength;
    }

    public Ability getStr() {
        return this.str;
    }

    public void setStrength(int strength) {
        this.str = new Ability(strength);

        this.strength = strength;
    }

    public int getDexterity() {
        return dexterity;
    }

    public Ability getDex() {
        return dex;
    }

    public void setDexterity(int dexterity) {
        this.dex = new Ability(dexterity);

        this.dexterity = dexterity;
    }


    public int getConstitution() {
        return constitution;
    }

    public Ability getCon() {
        return con;
    }

    public void setConstitution(int constitution) {
        this.con = new Ability(constitution);
        this.constitution = constitution;
    }

    public int getWisdom() {
        return wisdom;
    }

    public Ability getWis() {
        return wis;
    }

    public void setWisdom(int wisdom) {
        this.wis = new Ability(wisdom);
        this.wisdom = wisdom;
    }

    public int getIntelligence() {
        return intelligence;
    }

    public Ability getIntel() {
        return intel;
    }

    public void setIntelligence(int intelligence) {
        this.intel = new Ability(intelligence);
        this.intelligence = intelligence;
    }

    public int getCharisma() {
        return charisma;
    }

    public Ability getCha() {
        return cha;
    }

    public void setCharisma(int charisma) {
        this.cha = new Ability(charisma);
        this.charisma = charisma;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.setXP((level - 1) * 1000);
    }

    public int getXP() {
        return xp;
    }

    public void setXP(int xp) {
        this.xp = xp;

        updateLevel();

    }

    public void addXP(int xp) {
        this.xp += xp;
    }

    protected void addLevel(int xp) {
        int xpToLevel = xp / 1000;
        this.level = xpToLevel + 1;
    }

    public int getCritModifier(){ return this.critModifier; }

    public boolean canIgnoreDex(){return this.IgnoreDex;}

    public int getLevelMod() {
        return this.level / 2;
    }

    public int calculateDamage() {
        int damage = 1;
        int modifier = this.str.getModifier();

        if(modifier > 0) {
            damage += modifier;
        }
        return damage;
    }

    public boolean isCharacterDead() {
        return getHitPoints() <= 0;
    }

    public int getTotalRoll(int naturalRoll) {
        int strengthMod = this.str.getModifier();
        int levelMod = getLevelMod();

        return naturalRoll + strengthMod + levelMod;
    }

    public void updateHealth() {
        int modifiedHP = this.level * (5 + this.getCon().getModifier());

        setHitPoints(Math.max(modifiedHP, 1));
    }
    public void updateArmor() {
        setArmor(armor + this.dex.getModifier());
    }

    public void updateLevel() {
        this.addLevel(this.xp);
    }

    public void updateCharacter() {
        updateHealth();
        updateArmor();
        updateLevel();
    }
}
