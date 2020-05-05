package ca.flearning.restfulgloom.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="PERKS")
public class Perk {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long perkId;
	
	public Perk() {}

	public long getPerkId() {
		return perkId;
	}

	public void setPerkId(int perkId) {
		this.perkId = perkId;
	}
	
	
}
