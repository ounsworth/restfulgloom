package ca.flearning.restfulgloom.security;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class JWTAuthenticationFilter extends BasicAuthenticationFilter {

    public static final String AUTH_HEADER = "Authorization";

    public JWTAuthenticationFilter(AuthenticationManager authManager) { super(authManager); }

    @Override
    protected void doFilterInternal(HttpServletRequest req,
                                    HttpServletResponse res,
                                    FilterChain chain) throws IOException, ServletException {
        try {
            String token = req.getHeader(AUTH_HEADER);
            if (token == null) {
                throw new BadCredentialsException("No " + AUTH_HEADER + " header");
            }

            // parse the token.
            JWTToken authentication = new JWTToken(token);
            SecurityContextHolder.getContext().setAuthentication(authentication);
        } catch (BadCredentialsException e) {
            System.out.println("Auth rejected because: "+e.getMessage());
        } catch (Exception e) {
            // do nothing to fail the authentication
           e.printStackTrace();
        }
        chain.doFilter(req, res);
    }
}