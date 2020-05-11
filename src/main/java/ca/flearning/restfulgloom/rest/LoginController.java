package ca.flearning.restfulgloom.rest;

import java.util.Date;
import java.util.concurrent.atomic.AtomicLong;

import ca.flearning.restfulgloom.security.JWT;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {


    @PostMapping("/api/v1/login")
    public String greeting(@RequestParam(value = "name", defaultValue = "World") String name) {
        // TODO: return a JWT
        String token = Jwts.builder()
                .setSubject(name)
                .setExpiration(new Date(System.currentTimeMillis() + JWT.EXPIRATION_TIME))
                .setIssuer(JWT.ISSUER)
                .setAudience(JWT.ISSUER)
                .signWith(SignatureAlgorithm.HS256, JWT.SECRET)
                .compact();


        //TODO: use spring/jackson
        return "{\"token\": \""+token+"\"}";
    }
}