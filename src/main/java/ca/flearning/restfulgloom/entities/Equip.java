package ca.flearning.restfulgloom.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="CHARACTERS_ITEMS")
@IdClass(EquipId.class)
public class Equip{

	@Id
	@ManyToOne(optional=false, fetch = FetchType.LAZY)
	@JoinColumn(name="character_id")
	private Character equipedTo;
	
	@Id
	@ManyToOne(optional=false)
	@JoinColumn(name="item_id")
	private Item equipedItem;
	
	@Column(name="equip_slot", nullable=false)
	private String equipSlot = "";

	public Equip() {}
	public Equip(Character equipedTo, String equipSlot, Item equipedItem) {
		this.equipedTo = equipedTo;
		this.equipSlot = equipSlot;
		this.equipedItem = equipedItem;
	}

	@Override
	public String toString() {
		return equipedTo.getCharacterId() + "[" + equipSlot + "]=" + equipedItem.getItemId();
	}
	
	
}
