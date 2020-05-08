package ca.flearning.restfulgloom.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import ca.flearning.restfulgloom.entities.AbilityActionLine;

@RepositoryRestResource(exported = false)
public interface AbilityActionLineRepository extends JpaRepository<AbilityActionLine, Long>{

}
