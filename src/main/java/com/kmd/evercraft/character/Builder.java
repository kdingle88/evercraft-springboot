package com.kmd.evercraft.character;

public interface Builder {
    void reset();
    AdventurerBuilder setId(Long id);
    AdventurerBuilder setName(String name);
    AdventurerBuilder setAlignment(AdventurerAlignment alignment) throws Exception;
    AdventurerBuilder setArmor(int armor);
    AdventurerBuilder setHitPoints(int hp);
    AdventurerBuilder setStrength(int strength);
    AdventurerBuilder setDexterity(int dexterity);
    AdventurerBuilder setWisdom(int wisdom);
    AdventurerBuilder setIntelligence(int intelligence);
    AdventurerBuilder setConstitution(int constitution);
    AdventurerBuilder setCharisma(int charisma);
    AdventurerBuilder setXP(int xp);
}
