package ca.flearning.restfulgloom.comp;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;

import ca.flearning.restfulgloom.entities.Item;
import ca.flearning.restfulgloom.entities.Note;

public class ItemInfoConverter {
    	
	private int id;
	private String name;
	private int count;
	private int cost;
	private String slot;
	private String source;
	private boolean spent;
	private boolean consumed;
	private int minusOneCardsAdded;
	private int useSlots;
	private String desc;
	private String faq;
	private ItemSummonInfoConverter summon;
	
	public ItemInfoConverter() {}
	
	public static void hardCodedConversion() {
		ItemInfoConverterJSONConvert(
				new File("src/main/resources/data/items.json"),
				new File("src/main/resources/data/itemsConverted.json"),
				false);
		ItemInfoConverterJSONConvert(
				new File("src/main/resources/data/items.json"),
				new File("src/main/resources/data/itemsConvertedPretty.json"),
				true);
	}
	
	public static void ItemInfoConverterJSONConvert(File source, File target, boolean pretty) {
		ObjectMapper mapper = new ObjectMapper();
		
		try {
			
			ItemInfoConverter[] iic = mapper.readValue(source, ItemInfoConverter[].class);
			
			List<Item> itemList = new ArrayList<Item>();
			Arrays.stream(iic).forEach(item -> itemList.add(item.getAsItemEntity()));
			
			if(!pretty) mapper.writeValue(target, itemList);
			else mapper.writerWithDefaultPrettyPrinter().writeValue(target, itemList);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public Item getAsItemEntity() {
		Item result = new Item();
		result.setItemId(getId());
		result.setName(getName());
		result.setCost(getCost());
		result.setCount(getCount());
		result.setSlot(getSlot());
		result.setDescription(getDesc());
		result.setSource(getSource());
		result.setConsumed(isConsumed());
		result.setSpent(isSpent());
		
		if(getMinusOneCardsAdded() > 0)
			result.setMinusOneCardsAdded(getMinusOneCardsAdded());
		if(getUseSlots() > 0)
			result.setTokenSlots(getUseSlots());
		
		if(getSummon() != null) {
			result.setSummonAttack(getSummon().getAttack());
			result.setSummonHp(getSummon().getHp());
			result.setSummonMove(getSummon().getMove());
			result.setSummonRange(getSummon().getRange());
		}
		
		String faq = getFaq();
		if(faq != null) {
			if(faq.startsWith("[Errata] ")) {
				result.getNotes().add(new Note("Errata", faq.substring(9)));
			}else {
				result.getNotes().add(new Note("FAQ", faq));
			}
		}
		
		return result;
	}
	
	@Override
	public String toString() {
		return getId() + ":" + getName();
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public int getCost() {
		return cost;
	}

	public void setCost(int cost) {
		this.cost = cost;
	}

	public String getSlot() {
		return slot;
	}

	public void setSlot(String slot) {
		this.slot = slot;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public boolean isSpent() {
		return spent;
	}

	public void setSpent(boolean spent) {
		this.spent = spent;
	}

	public boolean isConsumed() {
		return consumed;
	}

	public void setConsumed(boolean consumed) {
		this.consumed = consumed;
	}

	public int getMinusOneCardsAdded() {
		return minusOneCardsAdded;
	}

	public void setMinusOneCardsAdded(int minusOneCardsAdded) {
		this.minusOneCardsAdded = minusOneCardsAdded;
	}

	public int getUseSlots() {
		return useSlots;
	}

	public void setUseSlots(int useSlots) {
		this.useSlots = useSlots;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public String getFaq() {
		return faq;
	}

	public void setFaq(String faq) {
		this.faq = faq;
	}

	public ItemSummonInfoConverter getSummon() {
		return summon;
	}

	public void setSummon(ItemSummonInfoConverter summon) {
		this.summon = summon;
	}

	

}
