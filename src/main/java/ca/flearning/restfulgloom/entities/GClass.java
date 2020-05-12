package ca.flearning.restfulgloom.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name="CLASSES")
public class GClass {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long classId = -1;
	
	@Column(name="name")
	private String name;
	
	@Column(name="race")
	private String race;
	
	// Graigheart Example: "#lvl * 2 + 8"
	@Column(name="health_expresssion")
	private String healthExpresssion;
	
	@Column(name="image_path")
	private String imgPath;
	
	@ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "CLASSES_NOTES", 
             joinColumns = { @JoinColumn(name = "class_id") }, 
             inverseJoinColumns = { @JoinColumn(name = "note_id") })
	private List<Note> notes = new ArrayList<Note>();
	
	public GClass() {}

	public long getClassId() {
		return classId;
	}

	public void setClassId(long classId) {
		this.classId = classId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getHealthExpresssion() {
		return healthExpresssion;
	}

	public void setHealthExpresssion(String healthExpresssion) {
		this.healthExpresssion = healthExpresssion;
	}

	public String getImgPath() {
		return imgPath;
	}

	public void setImgPath(String imgPath) {
		this.imgPath = imgPath;
	}

	public List<Note> getNotes() {
		return notes;
	}

	public void setNotes(List<Note> notes) {
		this.notes = notes;
	}
}
