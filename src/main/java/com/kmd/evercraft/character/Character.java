package com.kmd.evercraft.character;

import javax.persistence.*;
import java.util.HashMap;
import java.util.Map;

@Entity
public class Character {
    @SequenceGenerator(name = "character_sequence",sequenceName = "character_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "character_sequence")
    @Id
    private Long id;
    private String name;
    private String alignment;

    private int armor = 10;

    private int hitPoints = 5;

    private int strength = 10;

    private int dexterity = 10;
    private int constitution = 10;
    private int wisdom = 10;
    private int intelligence = 10;
    private int charisma = 10;

    public Character(String name) {
    }

    public Character() {
    }

    public Character(Long id, String name, String alignment) {
        this.id = id;
        this.name = name;
        this.alignment = alignment;
    }

    public Character(String name, String alignment) {
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

    public String getAlignment() {
        return alignment;
    }

    public void setAlignment(String alignment) {
        this.alignment = alignment;
    }

    public int getArmor() {
        return armor;
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

    public int getConstitution() {
        return constitution;
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
        Map<Integer, Integer> modifierTable = new HashMap();

        modifierTable.put(1, -5);
        modifierTable.put(10, 0);
        modifierTable.put(12, 1);

        return modifierTable.get(score);
    }
}
