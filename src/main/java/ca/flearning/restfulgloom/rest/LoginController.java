package ca.flearning.restfulgloom.rest;

import ca.flearning.restfulgloom.dao.RefreshTokenRepository;
import ca.flearning.restfulgloom.security.JWTToken;
import ca.flearning.restfulgloom.entities.RefreshToken;
import ca.flearning.restfulgloom.security.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/auth")
public class LoginController {

    @Autowired
    private RefreshTokenRepository refreshTokenRepository;

    @PostMapping("/devlogin")
    public JwtAndRefresh devLogin(@RequestParam(value = "name", defaultValue = "World") String name)
    {
        User user = new User();
        user.setName(name);
        JWTToken jwt = new JWTToken(user);


        // create a refresh token and persist it to the db

        RefreshToken refreshToken = RefreshToken.generateToken();
        refreshToken.setUsername(user.getName());
        try {
            refreshTokenRepository.save(refreshToken);
        } catch (Exception e) {
            // probably the random token happened to be a duplicate, and the DB threw
            // a constraint violation.
            // Try again.

            refreshToken = RefreshToken.generateToken();
            refreshToken.setUsername(user.getName());
            refreshTokenRepository.save(refreshToken);
        }

        // TODO: this should be HATEOAS and return a link to the refresh URI (complete with token path)
        // TODO: or should login be an exception to HATEOAS?
        return new JwtAndRefresh(jwt.getCredentials(), refreshToken);
    }


    @GetMapping("/refresh/{refreshToken}")
    public JWTToken.Token refresh(@PathVariable("refreshToken") String refreshTokenStr) {
        JWTToken token;

        List<RefreshToken> refreshTokenList = refreshTokenRepository.findByToken(refreshTokenStr);

        if (refreshTokenList.size() == 0) {
            throw new ForbiddenException("Refresh token not valid");
        } else if (refreshTokenList.size() == 1) {
            // process it and return a new JWT
            RefreshToken refreshToken = refreshTokenList.get(0);

            // is it expired?
            if (refreshToken.getExpiry().before(new Date(System.currentTimeMillis())) ) {
                throw new ForbiddenException("Refresh token expired");
            }

            // otherwise, it's good!
            User user = new User();
            user.setName(refreshToken.getUsername());
            token = new JWTToken(user);
        } else {
            // uhh, panic? The DB should enforce that as a unique field.
            throw new RuntimeException("DB should never have multiple entries for the same refresh token");
        }

        return token.getCredentials();
    }


    class JwtAndRefresh {
        String token;
        String refresh;

        public JwtAndRefresh(String token, String refresh) {
            this.token = token;
            this.refresh = refresh;
        }

        public JwtAndRefresh(JWTToken.Token jwttoken, RefreshToken refreshtoken) {
            this.token = jwttoken.getToken();
            this.refresh = refreshtoken.getToken();
        }

        public String getToken() {
            return token;
        }

        public void setToken(String token) {
            this.token = token;
        }

        public String getRefresh() {
            return refresh;
        }

        public void setRefresh(String refresh) {
            this.refresh = refresh;
        }
    }
    
    
}