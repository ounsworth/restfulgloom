package ca.flearning.restfulgloom.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="ITEMS")
public class Item implements Serializable{

	private static final long serialVersionUID = 3658814845790529884L;

	@Id
	private long itemId;
	
	@Column(name="name", nullable=false)
	private String name = "";
	
	@Column(name="item_catagory", nullable=false)
	private String itemCatagory = "";
	
	@Column(name="image_path", nullable=true)
	private String imgPath;
	
	public Item() {}
	
	public Item(long itemId, String name) {
		this.itemId = itemId;
		this.name = name;
		this.itemCatagory = "bleh";
	}

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

	public String getImgPath() {
		return imgPath;
	}

	public void setImgPath(String imgPath) {
		this.imgPath = imgPath;
	}
	
	@Override
	public int hashCode() {
		return Long.hashCode(this.itemId);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		
		Item itemObj = (Item)obj;
		if(this.itemId != itemObj.getItemId())
			return false;
		
		return true;
	}
}
