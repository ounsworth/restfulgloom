package ca.flearning.restfulgloom;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


/*******************
 *  @SpringBootApplication annotation is really just a grouping of three other annotations.
 *  
 *  @Configuration: Tags the class as a source of bean definitions for the application context.
 *  @EnableAutoConfiguration: Tells Spring Boot to start adding beans based on classpath settings, 
 *  	other beans, and various property settings. For example, if spring-webmvc is on the classpath, 
 *  	this annotation flags the application as a web application and activates key behaviors, such 
 *  	as setting up a DispatcherServlet.
 *  @ComponentScan: Tells Spring to look for other components, configurations, and services in the 
 *  	ca.flearning.restfulgloom package, letting it find the controllers. (This is, apparently, an
 *  	intensive operation on startup. It's recommended only to scan packages that use the Spring
 *  	annotations. I think in a proper production-ready service, I'd define most all the beans in
 *  	java config files and REALLY minimize ComponentScan. As is, it's a concern for a later date)
 *******************/
@SpringBootApplication
public class RestfulgloomApplication{
	
	/*******************
	 * Entry point for our application. 
	 * Are you not entertained?
	 *******************/
	public static void main(String[] args) {
		
		/*
		 * By default, this is what SpringApplication does for us;
		 * 
		 * 		1. Create an appropriate ApplicationContext instance (depending on your classpath)
		 * 		2. Register a CommandLinePropertySource to expose command line arguments as Spring properties
		 * 		3. Refresh the application context, loading all singleton beans
		 * 		4. Trigger any CommandLineRunner beans
		 * 
		 * We COULD customize this via
		 * 		SpringApplication application = new SpringApplication(MyApplication.class);
		 * 		... customize application settings ...
		 * 		application.run(args)
		 */
		SpringApplication.run(RestfulgloomApplication.class, args);
	}
}
