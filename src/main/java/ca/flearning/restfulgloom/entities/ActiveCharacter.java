package ca.flearning.restfulgloom.entities;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFilter;

@JsonFilter("JacksonIgnoreNullFalseZeroFilter")
@Entity
@Table(name="ACTIVE_CHARACTERS")
public class ActiveCharacter {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long activeCharacterId = -1;
	
	@ManyToOne(optional=false, fetch = FetchType.LAZY)
	@JoinColumn(name="character_id")
	private GCharacter baseCharacter;
	
	@ManyToOne(cascade=CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name="wallet_id")
	private Wallet wallet;
	
	@ManyToOne(cascade=CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name="status_id")
	private Status status;
	
	@Column(name="active")
	private boolean active = false;
}
