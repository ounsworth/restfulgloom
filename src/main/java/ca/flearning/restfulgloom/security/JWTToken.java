package ca.flearning.restfulgloom.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.Collection;
import java.util.Date;

public class JWTToken implements Authentication {

    // Constants are probably a bad idea and should be put into application.properties?
    public static final long EXPIRATION_TIME = 900_000;

    //TODO: move this to a static initializer and a file
    //TODO: if file exists, use it, if not then create it
    public static final String SECRET = "SuperStrongSecret";
    public static final String ISSUER = "ca.flearning.restfulgloom";


    public class Token {
        String token;

        public Token(String token) {
            this.token = token;
        }

        public String getToken() {
            return token;
        }
    }


    public String token;
    private User user;
    private boolean isAuthenticated = false;

    public JWTToken(User user) {
        this.user = user;
    }

    /**
     *
     * TODO: unit tests to add:
     *   - JWT has had a byte altered: should throw an exception.
     *   - Various claims are wrong
     *
     * @param token
     */
    public JWTToken(String token) throws BadCredentialsException {
        this.token = token;

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
            if (token == null) {
                token = Jwts.builder()
                        .setSubject(user.getName())
                        .setExpiration(new Date(System.currentTimeMillis() + JWTToken.EXPIRATION_TIME))
                        .setIssuer(JWTToken.ISSUER)
                        .setAudience(JWTToken.ISSUER)
                        .signWith(SignatureAlgorithm.HS256, JWTToken.SECRET)
                        .compact();
            }
        } catch (Exception e) {
            return null;
        }
        return new Token(token);
    }

    @Override
    public Object getDetails() {
        throw new NotImplementedException();
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
