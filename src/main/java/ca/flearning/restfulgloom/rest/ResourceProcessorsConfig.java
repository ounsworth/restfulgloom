package ca.flearning.restfulgloom.rest;

import org.springframework.hateoas.EntityModel;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.hateoas.server.RepresentationModelProcessor;

import ca.flearning.restfulgloom.entities.GClass;

@Configuration
public class ResourceProcessorsConfig {
	
	@Bean
	public RepresentationModelProcessor<EntityModel<GClass>> classProcessor() {
		return new RepresentationModelProcessor<EntityModel<GClass>>() {
			@Override
		  	public EntityModel<GClass> process(EntityModel<GClass> em) {
				//em.add(new Link("https://github.com/", "github-link"));
		    	return em;
		  	}
		};
	}
	
	
}