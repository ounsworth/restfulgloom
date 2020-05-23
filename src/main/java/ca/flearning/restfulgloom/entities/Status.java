package ca.flearning.restfulgloom.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFilter;

@JsonFilter("JacksonIgnoreNullFalseZeroFilter")
@Entity
@Table(name="STATUS")
public class Status {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long statusId = -1;
	
	@Column(name="poisoned")
	private boolean poisoned = false;
	@Column(name="wounded")
	private boolean wounded = false;
	@Column(name="immobilized")
	private boolean immobilized = false;
	@Column(name="disarmed")
	private boolean disarmed = false;
	@Column(name="stunned")
	private boolean stunned = false;
	@Column(name="muddled")
	private boolean muddled = false;
	@Column(name="invisible")
	private boolean invisible = false;
	@Column(name="stregthened")
	private boolean stregthened = false;
	@Column(name="regenerating")
	private boolean regenerating = false;
	@Column(name="injured")
	private boolean injured = false;
	@Column(name="brittle")
	private boolean brittle = false;
	@Column(name="bane")
	private boolean bane = false;
	
	@Column(name="curse_count")
	private int curseCount = 0;
	@Column(name="bless_count")
	private int blessCount = 0;
	
	public Status() {}
	
	public long getStatusId() {
		return statusId;
	}
	public void setStatusId(long statusId) {
		this.statusId = statusId;
	}
	public boolean isPoisoned() {
		return poisoned;
	}
	public void setPoisoned(boolean poisoned) {
		this.poisoned = poisoned;
	}
	public boolean isWounded() {
		return wounded;
	}
	public void setWounded(boolean wounded) {
		this.wounded = wounded;
	}
	public boolean isImmobilized() {
		return immobilized;
	}
	public void setImmobilized(boolean immobilized) {
		this.immobilized = immobilized;
	}
	public boolean isDisarmed() {
		return disarmed;
	}
	public void setDisarmed(boolean disarmed) {
		this.disarmed = disarmed;
	}
	public boolean isStunned() {
		return stunned;
	}
	public void setStunned(boolean stunned) {
		this.stunned = stunned;
	}
	public boolean isMuddled() {
		return muddled;
	}
	public void setMuddled(boolean muddled) {
		this.muddled = muddled;
	}
	public boolean isInvisible() {
		return invisible;
	}
	public void setInvisible(boolean invisible) {
		this.invisible = invisible;
	}
	public boolean isStregthened() {
		return stregthened;
	}
	public void setStregthened(boolean stregthened) {
		this.stregthened = stregthened;
	}
	public boolean isRegenerating() {
		return regenerating;
	}
	public void setRegenerating(boolean regenerating) {
		this.regenerating = regenerating;
	}
	public boolean isInjured() {
		return injured;
	}
	public void setInjured(boolean injured) {
		this.injured = injured;
	}
	public boolean isBrittle() {
		return brittle;
	}
	public void setBrittle(boolean brittle) {
		this.brittle = brittle;
	}
	public boolean isBane() {
		return bane;
	}
	public void setBane(boolean bane) {
		this.bane = bane;
	}
	public int getCurseCount() {
		return curseCount;
	}
	public void setCurseCount(int curseCount) {
		this.curseCount = curseCount;
	}
	public int getBlessCount() {
		return blessCount;
	}
	public void setBlessCount(int blessCount) {
		this.blessCount = blessCount;
	}
}
