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

@Entity
@Table(name="ABILITY_ACTION_LINES")
public class AbilityActionLine {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long abilityActionId = 0;
	
	@ManyToOne(optional=false, cascade=CascadeType.ALL)
	@JoinColumn(name="ability_card_id")
	private AbilityCard abilityCard = new AbilityCard();
	
	@Column(name = "top")
	private boolean top = true;
	
	@Column(name="order_num", nullable=false)
	private int order = 0;
	
	@Column(name="description", nullable=false)
	private String description = "";
	
	@Column(name="max_enhancements", nullable=false)
	private int maxEnhancements = 0;
	
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
}
