package ca.flearning.restfulgloom.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.Date;

@Component
public class JWTToken implements Authentication {

	private static final long serialVersionUID = 4116293207053981326L;

	// Constants are probably a bad idea and should be put into application.properties?
    private static final long EXPIRATION_TIME = 900_000;  // 15 mins

    private static String SECRET = null;  // I would rather throw an NPE than silently use a weak key.
                                          // Should be set during boot by RestfulgloomRunner

    public static void setSECRET(String SECRET) {
        JWTToken.SECRET = SECRET;
    }

    public static final String ISSUER = "ca.flearning.restfulgloom";

    public static class Token {
        String token;

        /* Constructors */
        public Token() { }
        public Token(String token) { this.token = token; }

        /* Getters / Setters */
        public void setToken(String token) { this.token = token; }
        public String getToken() {
            return token;
        }
    }

    /*** Member Variables ***/

    private Token token = new Token();
    private User user;
    private boolean isAuthenticated = false;

    public JWTToken(User user) {
        this.user = user;
        this.token = new Token();
    }

    public JWTToken() {  }

    /**
     *
     * TODO: unit tests to add:
     *   - JWT has had a byte altered: should throw an exception.
     *   - Various claims are wrong
     *
     * @param token
     */
    public JWTToken(String token) throws BadCredentialsException {
        this.token.setToken(token);

        try {
            Jws<Claims> claims = Jwts.parser()
                    .setSigningKey(SECRET)
                    .parseClaimsJws(token);

            String issuer = claims.getBody().getIssuer();
            if (!ISSUER.equals(issuer)) {
                throw new BadCredentialsException("Wrong issuer");
            }

            String audience = claims.getBody().getAudience();
            if (!ISSUER.equals(audience)) {
                throw new BadCredentialsException("Wrong audience");
            }

            // User checks out, set them to authenticated.
            user = new User();
            user.setName(claims.getBody().getSubject());

            isAuthenticated = true;
        } catch (io.jsonwebtoken.ExpiredJwtException e) {
            throw new BadCredentialsException("Token expired");
        }
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // TODO .. ?
        return null;
    }

    /**
     * Returns the JWT as set by the caller, attempts to generate
     * it from the member variables if it's null.
     *
     * @return base64 JWT token
     */
    @Override
    public JWTToken.Token getCredentials() {
        try {
            if (this.token.getToken() == null) {
                this.token.setToken(Jwts.builder()
                        .setSubject(user.getName())
                        .setExpiration(new Date(System.currentTimeMillis() + JWTToken.EXPIRATION_TIME))
                        .setIssuer(JWTToken.ISSUER)
                        .setAudience(JWTToken.ISSUER)
                        .signWith(SignatureAlgorithm.HS256, JWTToken.SECRET)
                        .compact()
                );
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return this.token;
    }


    @Override
    public Object getDetails() {
        throw new UnsupportedOperationException();
    }

    @Override
    public User getPrincipal() {
        if (user != null) {
            return user;
        } else {
            return null;
        }
    }

    @Override
    public boolean isAuthenticated() {
        return isAuthenticated;
    }

    @Override
    public void setAuthenticated(boolean isAuthenticated) throws IllegalArgumentException {
        this.isAuthenticated = isAuthenticated;
    }

    @Override
    public String getName() {
        if (user != null) {
            return user.getName();
        } else {
            return "";
        }
    }

}
