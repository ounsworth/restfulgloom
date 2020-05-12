package ca.flearning.restfulgloom.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import ca.flearning.restfulgloom.entities.GCharacter;

@RepositoryRestResource(collectionResourceRel = "characters", path = "characters")
public interface GCharacterRepository extends JpaRepository<GCharacter, Long> {
}
