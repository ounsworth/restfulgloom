package ca.flearning.restfulgloom.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import ca.flearning.restfulgloom.entities.Character;

public interface CharacterRepository extends JpaRepository<Character, Long> {
}
