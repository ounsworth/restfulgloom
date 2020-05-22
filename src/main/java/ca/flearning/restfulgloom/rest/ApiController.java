package ca.flearning.restfulgloom.rest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.hateoas.Link;

import ca.flearning.restfulgloom.rest.hateoas.MessageModel;

@RestController
@RequestMapping("/api")
public class ApiController {
	
	@Value("${ca.flearning.restfulgloom.api.message.welcome}")
	private String welcomeMessage;
	
	@Value("${ca.flearning.restfulgloom.api.message.auth}")
	private String authenticateMessage;

	@GetMapping()
	public MessageModel rootApiEndpoint(){
		MessageModel topLinks = new MessageModel(welcomeMessage);
		
		topLinks.add(linkTo(ApiController.class).withSelfRel());
		topLinks.add(linkTo(methodOn(ApiController.class).auth()).withRel("authenticate"));
		topLinks.add(linkTo(ApiController.class).slash("dm").withRel("datamodel"));
		topLinks.add(Link.of("https://github.com/Trequetrum/RestfulGloom", "github"));
		return topLinks;
	}
	
	@GetMapping("/auth")
	public MessageModel auth(){
		MessageModel authenticateLinks = new MessageModel(authenticateMessage);
		
		authenticateLinks.add(linkTo(methodOn(ApiController.class).auth()).withSelfRel());
		authenticateLinks.add(linkTo(methodOn(ApiController.class).rootApiEndpoint()).withRel("api"));
		
		return authenticateLinks;
		
	}
}
