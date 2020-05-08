package ca.flearning.restfulgloom;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.stream.Stream;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import ca.flearning.restfulgloom.entities.AbilityActionLine;
import ca.flearning.restfulgloom.entities.AbilityCard;
import ca.flearning.restfulgloom.entities.Character;
import ca.flearning.restfulgloom.entities.Equip;
import ca.flearning.restfulgloom.entities.GClass;
import ca.flearning.restfulgloom.entities.Item;
import ca.flearning.restfulgloom.entities.Note;
import ca.flearning.restfulgloom.entities.Perk;

@Component
public class RestfulgloomRunner implements CommandLineRunner{

	@Autowired // Tell the application-context to inject our EMF
	private EntityManagerFactory entityManagerFactory;
	
	/****
	 * Not sure where this kinda thing really belongs. 
	 * Going to throw some data in the H2 database so that I have something to look at/ test with
	 * It's all nonsense data, but JPA should keep it all in check for us.
	 * 
	 * Also, I'm not using any Java logging functionality here. This entire class is not meant to be
	 * production code.
	 */
	@Override
	public void run(String... args) throws Exception {
		
		System.out.println("    >> CommandLineRunner");
        Random rand = new Random();
        
        // Start a DB transaction
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();
        
        // Generate list of items. They're just named with numbers.
        // Clearly not real items
        List<Item> li = new ArrayList<Item>();
        for(int i = 0; i < 10; i++) {
        	li.add(new Item(i, "Item#000" + i));
        }
        for(int i = 10; i <= 30; i++) {
        	li.add(new Item(i, "Item#00" + i));
        }
        
        // Persist these items in the DB
        li.forEach(o -> em.persist(o));
        
        // Generate some characters, give them a name, note, and some random items
        List<Character> lc = new ArrayList<Character>();
        lc.add(genRandomChar("Miles", "This Character is going places", li));
        lc.add(genRandomChar("Golum", "My precious?", li));
        lc.add(genRandomChar("3mily", "ZAP ZAP ZAP!", li));
        lc.add(genRandomChar("Yap", "YapYapYapYapYapYapYapYapYapYapYapYapYapYapYapYap", li));
        lc.add(genRandomChar("McHammer", "Can't touch this", li));
        lc.add(genRandomChar("Adrian", "Give me all dem itamz plz!", li));
        lc.add(genRandomChar("Mike", "Nice try Mike", li));
        lc.add(genRandomChar("Kiss", "Out of love, there's nobody around, all I hear is the sound of a broken heart", li));
        lc.add(genRandomChar("Hoobastank", "I'm not a perfect person", li));
        lc.add(genRandomChar("Verbose", prettyLongString(), li));
       
     	// Persist these characters in the DB
        lc.forEach(o -> em.persist(o));
        
        // Generate some Ability Action lines (Not sure if I'm into these yet, but meh, we'll see where it goes.)
        List<AbilityActionLine> laal = new ArrayList<AbilityActionLine>();
        for(Character c : lc) {
        	for(AbilityCard ac : c.getAbilityCards()) {
        		AbilityActionLine newline1 = new AbilityActionLine();
        		newline1.setAbilityCard(ac);
        		newline1.setMaxEnhancements(rand.nextInt(3));
        		newline1.setOrder(0);
        		newline1.setDescription(ac.getName() + ": top of card line 1");
        		newline1.setTop(true);
        		AbilityActionLine newline2 = new AbilityActionLine();
        		newline2.setAbilityCard(ac);
        		newline2.setMaxEnhancements(rand.nextInt(3));
        		newline2.setOrder(0);
        		newline2.setDescription(ac.getName() + ": buttom of card line 1");
        		newline2.setTop(false);
        		AbilityActionLine newline3 = new AbilityActionLine();
        		newline3.setAbilityCard(ac);
        		newline3.setMaxEnhancements(rand.nextInt(3));
        		newline3.setOrder(1);
        		newline3.setDescription(ac.getName() + ": buttom of card line 2");
        		newline3.setTop(false);
        		laal.add(newline1);
        		laal.add(newline2);
        		laal.add(newline3);
        	}
        }
        
        // Persist these ability action lines in the DB
        laal.forEach(o -> em.persist(o));
        
        // Commit the persisted changes and close our DB connection
        em.getTransaction().commit();
        em.close();
        
        System.out.println("    >> CommandLineRunner done");
    }
	
	private Character genRandomChar(String name, String note, List<Item> li) {
		
		Random rand = new Random(); 
		 
		Character rtn = new Character();
		rtn.setName(name);
		rtn.setHealth(rand.nextInt(18));
		rtn.setExp(50 + rand.nextInt(150));
		rtn.setGold(10 + rand.nextInt(100));
		rtn.setCheckMarks(rand.nextInt(18));
		
		rtn.setCharClass(new GClass());
	
		// Give the character 3 different unique items. 
		Collections.shuffle(li);	
		if (li.size() > 2) {
			rtn.getEquiped().add(new Equip(rtn, "inventory", li.get(0)));
			rtn.getEquiped().add(new Equip(rtn, "inventory", li.get(1)));
			rtn.getEquiped().add(new Equip(rtn, "inventory", li.get(2)));
		}
		
		AbilityCard ac1 = new AbilityCard();
		ac1.setName(name + "'s First Ability");
		ac1.setLevel(rand.nextInt(8) + 1);
		ac1.setInitiative(rand.nextInt(98) + 1);
		rtn.getAbilityCards().add(ac1);
		
		AbilityCard ac2 = new AbilityCard();
		ac2.setName(name + "'s Second Ability");
		ac2.setLevel(rand.nextInt(8) + 1);
		ac2.setInitiative(rand.nextInt(98) + 1);
		rtn.getAbilityCards().add(ac2);
		
		AbilityCard ac3 = new AbilityCard();
		ac3.setName(name + "'s Third Ability");
		ac3.setLevel(rand.nextInt(8) + 1);
		ac3.setInitiative(rand.nextInt(98) + 1);
		rtn.getAbilityCards().add(ac3);
		
		rtn.getPerks().add(new Perk());
		rtn.getPerks().add(new Perk());
		rtn.getPerks().add(new Perk());
		
		rtn.getNotes().add(new Note(note));
		
		return rtn;
	}
	
	private String prettyLongString() {
		StringBuilder contentBuilder = new StringBuilder();
		
	    try (Stream<String> stream = Files.lines( Paths.get("src/main/resources/static/prettyLongString.txt"), StandardCharsets.UTF_8)) {
	        stream.forEach(s -> contentBuilder.append(s).append("\n"));
	    }catch (IOException e) {
	        e.printStackTrace();
	    }
	    
	    return contentBuilder.toString();
	}
	
	

}
