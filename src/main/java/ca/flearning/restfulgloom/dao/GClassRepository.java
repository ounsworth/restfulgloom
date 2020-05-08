package ca.flearning.restfulgloom.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import ca.flearning.restfulgloom.entities.GClass;

import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "classes", path = "classes")
public interface GClassRepository extends JpaRepository<GClass, Long>{

}
