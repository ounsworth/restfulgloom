package ca.flearning.restfulgloom.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="ITEMS")
public class Item {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long itemId;
	
	public Item() {}

	public long getItemId() {
		return itemId;
	}

	public void setItemId(int itemId) {
		this.itemId = itemId;
	}
	
}
