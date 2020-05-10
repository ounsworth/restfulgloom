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
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private DataSource datasource;

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception{
		auth.jdbcAuthentication().dataSource(datasource);

		System.out.println("    >> Configured AuthenticationManagerBuilder");
		System.out.println("    >> H2:     user:user");
		System.out.println("    >>         admin:admin");
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
			// root, home, & registration open to public
			.antMatchers("/", "/home", "/registration").permitAll()
			//allow h2 console access to admins only
			.antMatchers("/h2-console/**", "/api**").hasRole("ADMIN")
			.anyRequest().authenticated()
			.and().oauth2Login();
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

		System.out.println("    >> Configured HttpSecurity");
	}

	@Bean
	public PasswordEncoder passwordEncoder(){
		return new BCryptPasswordEncoder();
	}

	@Bean
	public ClientRegistrationRepository clientRegistrationRepository() {
		List<ClientRegistration> registrations = new ArrayList<>();
		registrations.add(intellitrustClientRegistration());
		return new InMemoryClientRegistrationRepository(registrations);
	}

	private ClientRegistration intellitrustClientRegistration() {
			return ClientRegistration.withRegistrationId("intellitrust")
          // .clientId("intellitrust-client-id")
					// .clientSecret("github-client-secret")
					.clientAuthenticationMethod(ClientAuthenticationMethod.BASIC)
					.authorizationGrantType(AuthorizationGrantType.AUTHORIZATION_CODE)
					.redirectUriTemplate("{baseUrl}/login/oauth2/code/{registrationId}")
					.authorizationUri("https://restfulgloom.us.trustedauth.com/api/oidc/OIDC/authorize")
					.tokenUri("https://restfulgloom.us.trustedauth.com/api/oidc/OIDC/token")
          .userInfoUri("https://www.googleapis.com/oauth2/v3/userinfo")
					.clientName("IntelliTrust").build();
	}
}
