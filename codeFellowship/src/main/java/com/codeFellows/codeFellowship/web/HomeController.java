package com.codeFellows.codeFellowship.web;


import com.codeFellows.codeFellowship.infrastructure.AppUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;

@Controller
public class HomeController {

    @Autowired
    AppUserRepository appUserRepository;

    @GetMapping("/")
    public String getHome(Principal principal , Model model){
        if(principal!=null){
            model.addAttribute("username", principal.getName());
            return "home";
        }
        return "home";
    }
}
