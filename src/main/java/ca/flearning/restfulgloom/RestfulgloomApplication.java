package ca.flearning.restfulgloom;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Random;
import java.util.stream.Stream;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import ca.flearning.restfulgloom.entities.Ability;
import ca.flearning.restfulgloom.entities.Note;
import ca.flearning.restfulgloom.entities.Perk;
import ca.flearning.restfulgloom.entities.Character;
import ca.flearning.restfulgloom.entities.GClass;
import ca.flearning.restfulgloom.entities.Item;

@SpringBootApplication
public class RestfulgloomApplication implements CommandLineRunner{

	/* Uncomment this if you feel like grabbing and inspecting any of your available beans.
	@Autowired
	private ApplicationContext applicationContext;
	*/
	@Autowired
	private EntityManagerFactory entityManagerFactory;
	
	
	public static void main(String[] args) {
		SpringApplication.run(RestfulgloomApplication.class, args);
	}
	
	
	/****
	 * Not sure where this kinda thing really belongs. 
	 * Going to throw some data in the H2 database so that I have something to look at/ test with
	 */
	@Override
    public void run(String... args) throws Exception {

        System.out.println("    >> CommandLineRunner");
        
        
        EntityManager em = entityManagerFactory.createEntityManager();
        
        Object[] el = new  Object[10];
        el[0] = genRandomChar("Miles", "This Character is going places");
        el[1] = genRandomChar("Golum", "My precious?");
        el[2] = genRandomChar("3mily", "ZAP ZAP ZAP!");
        el[3] = genRandomChar("Yap", "YapYapYapYapYapYapYapYapYapYapYapYapYapYapYapYap");
        el[4] = genRandomChar("McHammer", "Can't touch this");
        el[5] = genRandomChar("Adrian", "Give me all dem itamz plz!");
        el[6] = genRandomChar("Mike", "Nice try Mike");
        el[7] = genRandomChar("Kiss", "Out of love, there's nobody around, all I hear is the sound of a broken heart");
        el[8] = genRandomChar("Hoobastank", "I'm not a perfect person");
        el[9] = genRandomChar("Verbose", prettyLongString());
       
        em.getTransaction().begin();
        for(Object i : el) {
        	if(i != null) em.persist(i);
        }
        em.getTransaction().commit();
        em.close();
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
	
	private Character genRandomChar(String name, String note) {
		
		Random rand = new Random(); 
		 
		Character rtn = new Character();
		rtn.setName(name);
		rtn.setHealth(rand.nextInt(18));
		rtn.setExp(50 + rand.nextInt(150));
		rtn.setGold(10 + rand.nextInt(100));
		rtn.setCheckMarks(rand.nextInt(18));
		
		rtn.setCharClass(new GClass());
		
		rtn.getItems().add(new Item());
		rtn.getItems().add(new Item());
		rtn.getItems().add(new Item());
		
		rtn.getAbilities().add(new Ability());
		rtn.getAbilities().add(new Ability());
		rtn.getAbilities().add(new Ability());
		
		rtn.getPerks().add(new Perk());
		rtn.getPerks().add(new Perk());
		rtn.getPerks().add(new Perk());
		
		rtn.getNotes().add(new Note(note));
		
		return rtn;
	}

}
