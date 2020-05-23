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
@Table(name="WALLET")
public class Wallet {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long walletId = -1;
	
	@Column(name="money")
	private int money = 0;
	@Column(name="rock_root")
	private int rockRoot = 0;
	@Column(name="arrow_vine")
	private int arrowVine = 0;
	@Column(name="snow_thristle")
	private int snowThristle = 0;
	@Column(name="corpsecap")
	private int corpsecap = 0;
	@Column(name="axenut")
	private int axenut = 0;
	@Column(name="flamefruit")
	private int flamefruit = 0;
	@Column(name="hide")
	private int hide = 0;
	@Column(name="metal")
	private int metal = 0;
	@Column(name="wood")
	private int wood = 0;
	
	public Wallet() {}
	public Wallet(int money, int rockRoot, int arrowVine, int snowThristle, int corpsecap, int axenut,
			int flamefruit, int hide, int metal, int wood) {
		this.money = money;
		this.rockRoot = rockRoot;
		this.arrowVine = arrowVine;
		this.snowThristle = snowThristle;
		this.corpsecap = corpsecap;
		this.axenut = axenut;
		this.flamefruit = flamefruit;
		this.hide = hide;
		this.metal = metal;
		this.wood = wood;
	}


	public long getWalletId() {
		return walletId;
	}
	public void setWalletId(long walletId) {
		this.walletId = walletId;
	}
	public int getMoney() {
		return money;
	}
	public void setMoney(int money) {
		this.money = money;
	}
	public int getRockRoot() {
		return rockRoot;
	}
	public void setRockRoot(int rockRoot) {
		this.rockRoot = rockRoot;
	}
	public int getArrowVine() {
		return arrowVine;
	}
	public void setArrowVine(int arrowVine) {
		this.arrowVine = arrowVine;
	}
	public int getSnowThristle() {
		return snowThristle;
	}
	public void setSnowThristle(int snowThristle) {
		this.snowThristle = snowThristle;
	}
	public int getCorpsecap() {
		return corpsecap;
	}
	public void setCorpsecap(int corpsecap) {
		this.corpsecap = corpsecap;
	}
	public int getAxenut() {
		return axenut;
	}
	public void setAxenut(int axenut) {
		this.axenut = axenut;
	}
	public int getFlamefruit() {
		return flamefruit;
	}
	public void setFlamefruit(int flamefruit) {
		this.flamefruit = flamefruit;
	}
	public int getHide() {
		return hide;
	}
	public void setHide(int hide) {
		this.hide = hide;
	}
	public int getMetal() {
		return metal;
	}
	public void setMetal(int metal) {
		this.metal = metal;
	}
	public int getWood() {
		return wood;
	}
	public void setWood(int wood) {
		this.wood = wood;
	}
}
