package com.kmd.evercraft.character;

import javax.persistence.*;

@Entity
public class Character {
    @SequenceGenerator(name = "character_sequence",sequenceName = "character_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "character_sequence")
    @Id
    private Long id;
    private String name;
    private String alignment;

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
}
