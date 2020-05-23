package ca.flearning.restfulgloom.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFilter;

@JsonFilter("JacksonIgnoreNullFalseZeroFilter")
@Entity
@Table(name="PERKS")
public class Perk {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long perkId = -1;
	
	@Column(name="description")
	private String description = "";
	
	public Perk() {}
	
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setPerkId(long perkId) {
		this.perkId = perkId;
	}

	public long getPerkId() {
		return perkId;
	}

	public void setPerkId(int perkId) {
		this.perkId = perkId;
	}
	
	
}
