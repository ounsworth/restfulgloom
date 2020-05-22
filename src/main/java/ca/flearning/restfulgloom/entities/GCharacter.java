package ca.flearning.restfulgloom.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFilter;

@JsonFilter("JacksonIgnoreNullFalseZeroFilter")
@Entity
@Table(name="CHARACTERS")
public class GCharacter {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long characterId = -1;
	
	@Column(name="name", nullable=false)
	private String name = "No Name";
	@Column(name="exp")
	private int exp = -1;
	@Column(name="check_marks")
	private int checkMarks = -1;
	
	@ManyToOne(cascade=CascadeType.ALL, fetch=FetchType.LAZY)
	@JoinColumn(name="wallet_id")
	private Wallet wallet = new Wallet();
	
	@ManyToOne(cascade=CascadeType.PERSIST)
	@JoinColumn(name="class_id")
	private GClass characterClass = new GClass();
	
	@ManyToOne(cascade=CascadeType.PERSIST)
	@JoinColumn(name="personal_quest_id")
	private PersonalQuest personalQuest = new PersonalQuest();
	
	@OneToMany(mappedBy = "equipedTo", cascade = CascadeType.ALL)
	private List<Equip> equiped = new ArrayList<Equip>();
	
	@OneToMany(mappedBy = "baseCharacter", cascade = CascadeType.ALL)
	private List<ActiveCharacter> activeCharacters = new ArrayList<ActiveCharacter>();
	
	@ManyToMany(cascade=CascadeType.PERSIST)
    @JoinTable(name = "CHARACTERS_ABILITY_CARDS", 
             joinColumns = { @JoinColumn(name = "character_id") }, 
             inverseJoinColumns = { @JoinColumn(name = "ability_card_id") })
	private List<AbilityCard> abilityCards = new ArrayList<AbilityCard>();
	
	@ManyToMany(cascade=CascadeType.PERSIST)
    @JoinTable(name = "CHARACTERS_PERKS", 
             joinColumns = { @JoinColumn(name = "character_id") }, 
             inverseJoinColumns = { @JoinColumn(name = "perk_id") })
	private List<Perk> perks = new ArrayList<Perk>();
	
	@ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "CHARACTERS_NOTES", 
             joinColumns = { @JoinColumn(name = "character_id") }, 
             inverseJoinColumns = { @JoinColumn(name = "note_id") })
	private List<Note> notes = new ArrayList<Note>();
	
	public GCharacter() {}

	public long getCharacterId() {
		return characterId;
	}

	public void setCharacterId(long characterId) {
		this.characterId = characterId;
	}

	public int getExp() {
		return exp;
	}

	public void setExp(int exp) {
		this.exp = exp;
	}

	public int getCheckMarks() {
		return checkMarks;
	}

	public void setCheckMarks(int checkMarks) {
		this.checkMarks = checkMarks;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public GClass getCharClass() {
		return characterClass;
	}

	public void setCharClass(GClass charClass) {
		this.characterClass = charClass;
	}

	public List<Equip> getEquiped() {
		return equiped;
	}

	public void setEquiped(List<Equip> equiped) {
		this.equiped = equiped;
	}

	public List<AbilityCard> getAbilityCards() {
		return abilityCards;
	}

	public void setAbilityCards(List<AbilityCard> abilityCards) {
		this.abilityCards = abilityCards;
	}

	public List<Perk> getPerks() {
		return perks;
	}

	public void setPerks(List<Perk> perks) {
		this.perks = perks;
	}

	public List<Note> getNotes() {
		return notes;
	}

	public void setNotes(List<Note> notes) {
		this.notes = notes;
	}

	public Wallet getWallet() {
		return wallet;
	}

	public void setWallet(Wallet wallet) {
		this.wallet = wallet;
	}

	public GClass getCharacterClass() {
		return characterClass;
	}

	public void setCharacterClass(GClass characterClass) {
		this.characterClass = characterClass;
	}

	public PersonalQuest getPersonalQuest() {
		return personalQuest;
	}

	public void setPersonalQuest(PersonalQuest personalQuest) {
		this.personalQuest = personalQuest;
	}

	public List<ActiveCharacter> getActiveCharacters() {
		return activeCharacters;
	}

	public void setActiveCharacters(List<ActiveCharacter> activeCharacters) {
		this.activeCharacters = activeCharacters;
	}
	
	
}
