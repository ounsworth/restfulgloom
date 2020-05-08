package ca.flearning.restfulgloom.entities;

import java.io.Serializable;

public class EquipId implements Serializable{

	private static final long serialVersionUID = 1134443579119744890L;

	private Character equipedTo;
	private Item equipedItem;

	public EquipId() {}
	public EquipId(Character equipedTo, Item equipedItem) {
		this.equipedTo = equipedTo;
		this.equipedItem = equipedItem;
	}
	
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((equipedItem == null) ? 0 : Long.hashCode(equipedItem.getItemId()));
		result = prime * result + ((equipedTo == null) ? 0 : Long.hashCode(equipedTo.getCharacterId()));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		if(this.toString() != obj.toString())
			return false;
		
		return true;
	}
	
	@Override
	public String toString() {
		return ((equipedItem == null) ? 0 : equipedItem.getItemId()) 
				+ "::" 
				+ ((equipedTo == null) ? 0 : equipedTo.getCharacterId());
	}
}
