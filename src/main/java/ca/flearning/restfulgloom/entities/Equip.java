package ca.flearning.restfulgloom.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFilter;

@JsonFilter("JacksonIgnoreNullFalseZeroFilter")
@Entity
@Table(name="CHARACTERS_ITEMS")
@IdClass(EquipId.class)
public class Equip{

	@Id
	@ManyToOne(optional=false, fetch = FetchType.LAZY)
	@JoinColumn(name="character_id")
	private GCharacter equipedTo;
	
	@Id
	@ManyToOne(optional=false)
	@JoinColumn(name="item_id")
	private Item equipedItem;
	
	@Column(name="equipped")
	private boolean equipped = false;

	public Equip() {}
	public Equip(GCharacter equipedTo, boolean equipped, Item equipedItem) {
		this.equipedTo = equipedTo;
		this.equipped = equipped;
		this.equipedItem = equipedItem;
	}

	@Override
	public String toString() {
		String rtn = ((equipedTo == null) ? "Unknown" : equipedTo.getName());
		rtn += (equipped ? "[" + equipedItem.getSlot() + "] = " : "[unequipped] = ");
		return rtn + ((equipedTo == null) ? "Unknown" : equipedItem.getName());
	}
	
	public GCharacter getEquipedTo() {
		return equipedTo;
	}
	public void setEquipedTo(GCharacter equipedTo) {
		this.equipedTo = equipedTo;
	}
	public Item getEquipedItem() {
		return equipedItem;
	}
	public void setEquipedItem(Item equipedItem) {
		this.equipedItem = equipedItem;
	}
	public boolean isEquipped() {
		return equipped;
	}
	public void setEquipped(boolean equipped) {
		this.equipped = equipped;
	}
}
