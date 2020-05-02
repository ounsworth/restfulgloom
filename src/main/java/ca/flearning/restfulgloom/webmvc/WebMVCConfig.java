package ca.flearning.restfulgloom.webmvc;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

@Configuration
public class WebMVCConfig {

	@Bean
	public ViewResolver viewResolver() {
	    InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();

	    viewResolver.setPrefix("/resources/templates");
	    viewResolver.setSuffix(".html");

	    return viewResolver;
	}
}