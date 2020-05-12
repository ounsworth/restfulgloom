package ca.flearning.restfulgloom.rest;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurer;

import ca.flearning.restfulgloom.entities.Item;

@Configuration
public class RepositoryRestConfig implements RepositoryRestConfigurer {

	@Override
    public void configureRepositoryRestConfiguration(RepositoryRestConfiguration config) {
        config.exposeIdsFor(Item.class);
    }

}
