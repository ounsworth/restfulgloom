package ca.flearning.restfulgloom.entities;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFilter;

@JsonFilter("JacksonIgnoreNullFalseNegFilter")
@Entity
@Table(name="ABILITY_ACTION_LINES")
public class AbilityActionLine {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long abilityActionId = -1;
	
	@ManyToOne(optional=false, cascade=CascadeType.ALL)
	@JoinColumn(name="ability_card_id")
	private AbilityCard abilityCard = new AbilityCard();
	
	@Column(name = "top")
	private boolean top = false;
	
	@Column(name = "sub")
	private boolean sub = false;
	
	@Column(name="order_num")
	private int order = -1;
	
	@Column(name="description")
	private String description;
	
	@Column(name="max_enhancements")
	private int maxEnhancements = -1;
	
	public AbilityActionLine() {}

	public long getAbilityActionId() {
		return abilityActionId;
	}

	public void setAbilityActionId(long abilityActionId) {
		this.abilityActionId = abilityActionId;
	}

	public AbilityCard getAbilityCard() {
		return abilityCard;
	}

	public void setAbilityCard(AbilityCard abilityCard) {
		this.abilityCard = abilityCard;
	}

	public boolean isTop() {
		return top;
	}

	public void setTop(boolean top) {
		this.top = top;
	}

	public int getOrder() {
		return order;
	}

	public void setOrder(int order) {
		this.order = order;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getMaxEnhancements() {
		return maxEnhancements;
	}

	public void setMaxEnhancements(int maxEnhancements) {
		this.maxEnhancements = maxEnhancements;
	}

	public boolean isSub() {
		return sub;
	}

	public void setSub(boolean sub) {
		this.sub = sub;
	}
	
	
}
