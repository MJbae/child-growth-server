package mj.childGrowth.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {
    private final String WELCOME_MESSAGE = "Welcome to Child Growth API Server";

    @RequestMapping("/")
    public String welcomeMessage() {
        return WELCOME_MESSAGE;
    }
}
