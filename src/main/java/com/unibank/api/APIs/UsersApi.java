package com.unibank.api.APIs;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UsersApi {

    @GetMapping("/home")
    public String home(){
        return "Home";
    }
}
