package ca.flearning.restfulgloom.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="ABILITY_CARDS")
public class AbilityCard {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long abilityCardId;
	
	@Column(name="name", nullable=false)
	private String name = "";
	@Column(name="level", nullable=false)
	private int level = 0;
	@Column(name="initiative", nullable=false)
	private int initiative = 0;
	@Column(name="image_path", nullable=true)
	private String imgPath;
	
	public AbilityCard() {}

	public long getAbilityCardId() {
		return abilityCardId;
	}

	public void setAbilityCardId(long abilityCardId) {
		this.abilityCardId = abilityCardId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public int getInitiative() {
		return initiative;
	}

	public void setInitiative(int initiative) {
		this.initiative = initiative;
	}

	public String getImgPath() {
		return imgPath;
	}

	public void setImgPath(String imgPath) {
		this.imgPath = imgPath;
	}
}
