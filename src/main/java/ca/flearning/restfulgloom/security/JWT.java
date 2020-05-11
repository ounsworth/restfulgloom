package ca.flearning.restfulgloom.security;

import io.jsonwebtoken.Jwts;

public class JWT {

    // Constants are probably a bad idea and should be put into application.properties?
    public static final long EXPIRATION_TIME = 900_000;

    //TODO: move this to a static iniliaziser and a file
    //TODO: if file exists, use it, if not then create it
    public static final String SECRET = "SuperStrongSecret";

    public static final String ISSUER = "ca.flearning.restfulgloom";
}
