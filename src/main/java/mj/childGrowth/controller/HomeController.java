package mj.childGrowth.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
public class HomeController {
    private final String WELCOME_MESSAGE = "Welcome to Child Growth API Server";

    @RequestMapping("/api/")
    public String welcomeMessage() {
        return WELCOME_MESSAGE;
    }
}
