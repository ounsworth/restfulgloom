package ca.flearning.restfulgloom.security;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
	private DataSource datasource;

	@Override 
	protected void configure(AuthenticationManagerBuilder auth) throws Exception{
		auth.jdbcAuthentication().dataSource(datasource);
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
			// root, home, & registration open to public
			.antMatchers("/", "/home", "/registration", "/api", "/api/auth").permitAll()
			//allow h2 console access to admins only
			.antMatchers("/h2-console/**", "/api/dm**").hasRole("ADMIN")
			.anyRequest().authenticated();
		http.csrf()
			// Don't apply CSRF protection to /h2-console. 
			// Not safe, but hey - it's fine for development
			.ignoringAntMatchers("/h2-console/**");
		http.headers()
			//allow use of frame to same origin urls
			.frameOptions()
			.sameOrigin();
		http.formLogin()
			.loginPage("/login")
			.permitAll();
		http.logout()
			.permitAll();
		http.exceptionHandling().accessDeniedPage("/access-denied");
	}
	 
	@Bean
	public PasswordEncoder passwordEncoder(){
		return new BCryptPasswordEncoder();
	}
}