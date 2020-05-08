package ca.flearning.restfulgloom.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import ca.flearning.restfulgloom.entities.Note;

public interface NoteRepository extends JpaRepository<Note, Long>{
}
