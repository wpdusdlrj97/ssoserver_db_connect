package com.example.sso.ssoserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;


//we're going to run the Authorization Server and the Resource Server together here, as a single deployable unit.
//Let's start with the configuration of our Resource Server – which doubles as our primary Boot application:

// 리소스 서버 설정
@SpringBootApplication
@EnableResourceServer
public class SsoserverApplication {

    public static void main(String[] args) {
        SpringApplication.run(SsoserverApplication.class, args);
    }

}
