package ca.flearning.restfulgloom.webmvc;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.NoHandlerFoundException;

@Controller
public class RootController {

	@RequestMapping(value ="/")
	public String showHomePage(){
		return "home";
	}
	
	@RequestMapping(value ="/home")
	public String showHomePage2(){
		return "home";
	}
	
	@RequestMapping(value ="/hello")
	public String showHelloPage(){
		return "hello";
	}
	
	@RequestMapping(value ="/login")
	public String showLoginPage(){
		return "access/login";
	}
	
	@RequestMapping(value ="/access-denied")
	public String showAccessDenied(){
		return "access/access-denied";
	}
	
	@RequestMapping(value ="/registration")
	public String showRegistration(){
		return "access/registration";
	}
	
	@ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(NoHandlerFoundException.class)
    public String handleNoHandlerFoundException(NoHandlerFoundException ex) {
		System.out.println("here");
        //log.error("404 situation detected.",ex);
        return "Specified path not found on this server";
    }
	
}