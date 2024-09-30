package com.the_dapda.domain.code.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Secured("ROLE_ADMIN")
@Controller
public class AdminController {

    @Autowired
    PasswordEncoder passwordEncoder;

    @GetMapping("/pass")
    public String getPass(Model model) {

        return "admin";
    }
}
