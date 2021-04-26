package org.freecode.demo.controller;

import org.freecode.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {
    @Autowired
    private BCryptPasswordEncoder bcPassEnc;
    @GetMapping("/")
    public String getMainPage() {
        return "main";
    }

    @GetMapping("/login")
    public String getLoginPage() {
        // System.out.println(bcPassEnc.encode("tester"));
        return "login";
    }
}
