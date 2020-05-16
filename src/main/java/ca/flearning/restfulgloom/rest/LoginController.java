package ca.flearning.restfulgloom.rest;

import ca.flearning.restfulgloom.security.JWTToken;
import ca.flearning.restfulgloom.security.User;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {


    @PostMapping("/api/dev/login")
    public JWTToken.Token greeting(@RequestParam(value = "name", defaultValue = "World") String name)
    {
        User user = new User();
        user.setName(name);

        JWTToken jwt = new JWTToken(user);
        return jwt.getCredentials();
    }
}