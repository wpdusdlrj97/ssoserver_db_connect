package com.example.sso.ssoserver.config;

import java.security.Principal;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

//Finally, we will create our user endpoint we used earlier in our configuration:
//Naturally, this will return the user data with a JSON representation.
@RestController
public class UserController {

    @RequestMapping("/user/me")
    public Principal user(Principal principal) {
        System.out.println(principal);
        return principal;
    }
}
