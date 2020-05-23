package ca.flearning.restfulgloom.rest.hateoas;

import org.springframework.hateoas.RepresentationModel;

public class MessageModel extends RepresentationModel<MessageModel>{

	private String message;
	
	public MessageModel() {}
	public MessageModel(String message) {
		this.message = message;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
}
