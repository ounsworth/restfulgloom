package ca.flearning.restfulgloom.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="ABILITIES")
public class Ability {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long abilityId;
	
	public Ability() {}

	public long getAbilityId() {
		return abilityId;
	}

	public void setAbilityId(int abilityId) {
		this.abilityId = abilityId;
	}
	
	
}
