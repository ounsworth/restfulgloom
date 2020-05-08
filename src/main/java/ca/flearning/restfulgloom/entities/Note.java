package ca.flearning.restfulgloom.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

@Entity
@Table(name="NOTES")
public class Note {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long noteId;
	
	@Column(name="note", nullable=false)
	@Lob 
	private String note = "";
	
	public Note() {}
	public Note(String note) {
		this.note = note;
	}

	public long getNoteId() {
		return noteId;
	}

	public void setNoteId(int noteId) {
		this.noteId = noteId;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}
	
	
}
