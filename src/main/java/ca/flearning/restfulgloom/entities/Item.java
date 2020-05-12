package ca.flearning.restfulgloom.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name="ITEMS")
public class Item{
	@Id
	private long itemId = -1;
	
	@Column(name="name")
	private String name = "";
	@Column(name="cost")
	private int cost = -1;
	@Column(name="count")
	private int count = -1;
	@Column(name="slot")
	private String slot;
	@Column(name="description")
	private String description;
	@Column(name="spent")
	private boolean spent;
	@Column(name="consumed")
	private boolean consumed = false;
	@Column(name="source")
	private String source;
	@Column(name="token_slots")
	private int tokenSlots = -1;
	@Column(name="minus_one_cards")
	private int minusOneCardsAdded = -1;
	@Column(name="summon_hp")
	private int summonHp = -1;
	@Column(name="summon_move")
	private int summonMove = -1;
	@Column(name="summon_attack")
	private int summonAttack = -1;
	@Column(name="summon_range")
	private int summonRange = -1;
	
	@ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "ITEMS_NOTES", 
             joinColumns = { @JoinColumn(name = "item_id") }, 
             inverseJoinColumns = { @JoinColumn(name = "note_id") })
	private List<Note> notes = new ArrayList<Note>();
	
	@Column(name="image_path", nullable=true)
	private String imgPath;
	
	public Item() {}

	public long getItemId() {
		return itemId;
	}

	public void setItemId(long itemId) {
		this.itemId = itemId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getCost() {
		return cost;
	}

	public void setCost(int cost) {
		this.cost = cost;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public String getSlot() {
		return slot;
	}

	public void setSlot(String slot) {
		this.slot = slot;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public boolean isSpent() {
		return spent;
	}

	public void setSpent(boolean spent) {
		this.spent = spent;
	}

	public boolean isConsumed() {
		return consumed;
	}

	public void setConsumed(boolean consumed) {
		this.consumed = consumed;
	}

	public int getSummonHp() {
		return summonHp;
	}

	public void setSummonHp(int summonHp) {
		this.summonHp = summonHp;
	}

	public int getSummonMove() {
		return summonMove;
	}

	public void setSummonMove(int summonMove) {
		this.summonMove = summonMove;
	}

	public int getSummonAttack() {
		return summonAttack;
	}

	public void setSummonAttack(int summonAttack) {
		this.summonAttack = summonAttack;
	}

	public int getSummonRange() {
		return summonRange;
	}

	public void setSummonRange(int summonRange) {
		this.summonRange = summonRange;
	}

	public List<Note> getNotes() {
		return notes;
	}

	public void setNotes(List<Note> notes) {
		this.notes = notes;
	}

	public String getImgPath() {
		return imgPath;
	}

	public void setImgPath(String imgPath) {
		this.imgPath = imgPath;
	}
	
	public int getTokenSlots() {
		return tokenSlots;
	}

	public void setTokenSlots(int tokenSlots) {
		this.tokenSlots = tokenSlots;
	}

	public int getMinusOneCardsAdded() {
		return minusOneCardsAdded;
	}

	public void setMinusOneCardsAdded(int minusOneCardsAdded) {
		this.minusOneCardsAdded = minusOneCardsAdded;
	}
}
