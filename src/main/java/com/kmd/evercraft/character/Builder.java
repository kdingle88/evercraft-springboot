package com.kmd.evercraft.character;

public interface Builder {
    void reset();
    void setName(String name);
    void setAlignment(AdventurerAlignment alignment);
    void setArmor(int armor);
    void setHitPoints(int hp);
    void setStrength(int strength);
    void setDexterity(int dexterity);
    void setWisdom(int wisdom);
    void setIntelligence(int intelligence);
    void setConstitution(int constitution);
    void setCharisma(int charisma);
    void setXP(int xp);
}
