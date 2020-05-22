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
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFilter;

@JsonFilter("JacksonIgnoreNullFalseZeroFilter")
@Entity
@Table(name="ABILITY_CARDS")
public class AbilityCard {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long abilityCardId = -1;
	
	@Column(name="name")
	private String name;
	@Column(name="level")
	private int level = -1;
	@Column(name="initiative")
	private int initiative = -1;
	@Column(name="image_path")
	private String imgPath;
	
	@OneToMany(mappedBy = "abilityCard", cascade = CascadeType.ALL)
	private List<AbilityActionLine> actionLines = new ArrayList<AbilityActionLine>();
	
	@ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "ABILITY_CARDS_NOTES", 
             joinColumns = { @JoinColumn(name = "ability_card_id") }, 
             inverseJoinColumns = { @JoinColumn(name = "note_id") })
	private List<Note> notes = new ArrayList<Note>();
	
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

	public List<AbilityActionLine> getActionLines() {
		return actionLines;
	}

	public void setActionLines(List<AbilityActionLine> actionLines) {
		this.actionLines = actionLines;
	}

	public List<Note> getNotes() {
		return notes;
	}

	public void setNotes(List<Note> notes) {
		this.notes = notes;
	}
	
}
