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
import javax.persistence.ManyToOne;
import javax.persistence.OrderColumn;
import javax.persistence.Table;


@Entity
@Table(name="CHARACTERS")
public class Character {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long characterId;
	
	@Column(name="health", nullable=false, unique=false)
	private int health = 0;
	@Column(name="exp", nullable=false, unique=false)
	private int exp = 0;
	@Column(name="gold", nullable=false, unique=false)
	private int gold = 0;
	@Column(name="check_marks", nullable=false, unique=false)
	private int checkMarks = 0;
	@Column(name="name", nullable=false, unique=false)
	private String name = "No Name";

	@ManyToOne(optional=false, cascade=CascadeType.ALL)
	@JoinColumn(name="class_id")
	private GClass charClass = new GClass();
	
	@ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "CHARACTER_ITEM", 
             joinColumns = { @JoinColumn(name = "character_id") }, 
             inverseJoinColumns = { @JoinColumn(name = "item_id") })
	private List<Item> items = new ArrayList<Item>();
	
	@ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "CHARACTER_ABILITY", 
             joinColumns = { @JoinColumn(name = "character_id") }, 
             inverseJoinColumns = { @JoinColumn(name = "ability_id") })
	private List<Ability> abilities = new ArrayList<Ability>();
	
	@ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "CHARACTER_PERK", 
             joinColumns = { @JoinColumn(name = "character_id") }, 
             inverseJoinColumns = { @JoinColumn(name = "perk_id") })
	private List<Perk> perks = new ArrayList<Perk>();
	
	@ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "CHARACTER_NOTE", 
             joinColumns = { @JoinColumn(name = "character_id") }, 
             inverseJoinColumns = { @JoinColumn(name = "note_id") })
	private List<Note> notes = new ArrayList<Note>();
	
	public Character() {}

	public long getCharacterId() {
		return characterId;
	}

	public void setCharacterId(long characterId) {
		this.characterId = characterId;
	}

	public int getHealth() {
		return health;
	}

	public void setHealth(int health) {
		this.health = health;
	}

	public int getExp() {
		return exp;
	}

	public void setExp(int exp) {
		this.exp = exp;
	}

	public int getGold() {
		return gold;
	}

	public void setGold(int gold) {
		this.gold = gold;
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
		return charClass;
	}

	public void setCharClass(GClass charClass) {
		this.charClass = charClass;
	}

	public List<Item> getItems() {
		return items;
	}

	public void setItems(List<Item> items) {
		this.items = items;
	}

	public List<Ability> getAbilities() {
		return abilities;
	}

	public void setAbilities(List<Ability> abilities) {
		this.abilities = abilities;
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
}
