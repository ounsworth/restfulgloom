package ca.flearning.restfulgloom.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="CLASSES")
public class GClass {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long classId;
	
	public GClass() {}

	public long getClassId() {
		return classId;
	}

	public void setClassId(int classId) {
		this.classId = classId;
	}
	
	
}
