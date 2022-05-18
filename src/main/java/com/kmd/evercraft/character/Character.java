package com.kmd.evercraft.character;

import javax.persistence.*;

@Entity
public class Character {
    @SequenceGenerator(name = "character_sequence",sequenceName = "character_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "character_sequence")
    @Id
    private Long id;
    private String name;
    private CharacterAlignment alignment;

    private int armor = 10;

    private int hitPoints = 5;

    private int strength = 10;

    private int dexterity = 10;
    private int constitution = 10;
    private int wisdom = 10;
    private int intelligence = 10;
    private int charisma = 10;
    protected int level = 1;
    private int xp = 0;


    public Character() {
    }

    public Character(Long id, String name, CharacterAlignment alignment) {
        this.id = id;
        this.name = name;
        this.alignment = alignment;
    }

    public Character(String name, CharacterAlignment alignment) {
        this.name = name;
        this.alignment = alignment;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public CharacterAlignment getAlignment() {
        return alignment;
    }

    public void setAlignment(CharacterAlignment alignment) {
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

    public boolean attack(Character characterAttacked, int roll) {
        return characterAttacked.armor <= roll;
    }

    public void setHitPoints(int hitPoints) {
        this.hitPoints = hitPoints;
    }

    public int getStrength() {
        return strength;
    }

    public void setStrength(int strength) {
        this.strength = strength;
    }

    public int getDexterity() {
        return dexterity;
    }

    public void setDexterity(int dexterity) {

        setArmor(armor + getModifier(dexterity));

        this.dexterity = dexterity;
    }

    public int getConstitution() {
        return constitution;
    }

    public void setConstitution(int constitution) {
        int modifiedHP = hitPoints + getModifier(constitution);

        setHitPoints(Math.max(modifiedHP, 1));

        this.constitution = constitution;
    }

    public int getWisdom() {
        return wisdom;
    }

    public int getIntelligence() {
        return intelligence;
    }

    public int getCharisma() {
        return charisma;
    }

    public static Integer getModifier(int score) {

        int modifier = (int)Math.floor(score / 2) - 5;

        return Integer.valueOf(modifier);
    }

    public int getLevel() {
        return level;
    }

    protected void setLevel(int xp) {

        int xpToLevel = xp / 1000;
        int newLevel = xpToLevel + 1;
        int baseHealthMultiplier = xpToLevel;

        if(level < newLevel) {
            level = newLevel;

            setHitPoints(getHitPoints() + (5 * baseHealthMultiplier + getModifier(getConstitution())));
        }
    }

    public int getXP() {
        return xp;
    }

    public void setXP(int xp) {
        this.xp = xp;

        this.setLevel(this.xp);

    }

    public void addXP(int xp) {
        this.xp += xp;

        this.setLevel(this.xp);
    }

    public void addLevel(int xp) {
        int xpToLevel = xp / 1000;
        int newLevel = xpToLevel + 1;
        int baseHealthMultiplier = xpToLevel;

        if(level < newLevel) {
            level = newLevel;

            setHitPoints(getHitPoints() + (5 * baseHealthMultiplier + getModifier(getConstitution())));

        }
    }
}
