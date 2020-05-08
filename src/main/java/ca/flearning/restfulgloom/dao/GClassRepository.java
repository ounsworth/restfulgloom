package ca.flearning.restfulgloom.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;

import ca.flearning.restfulgloom.entities.GClass;

@RepositoryRestResource(collectionResourceRel = "classes", path = "classes")
public interface GClassRepository extends JpaRepository<GClass, Long>{
}
