package com.evercraft;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdventurerRepository extends JpaRepository<Adventurer,Long> {
}
