package com.codeFellows.codeFellowship.web;


import com.codeFellows.codeFellowship.domain.ApplicationUser;
import com.codeFellows.codeFellowship.infrastructure.AppUserRepository;
import com.codeFellows.codeFellowship.infrastructure.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.view.RedirectView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

@Controller
public class ApplicationController {
    @Autowired
    AppUserRepository appUserRepository;

    @Autowired
    private PostRepository postRepository;

    @Autowired
    BCryptPasswordEncoder encoder;

    @GetMapping("/signup")
    public String getSignUpPage() {
        return "signup";
    }

    @GetMapping("/login")
    public String getLoginPage() {
        return "login";
    }

    @PostMapping("/signup")
    public RedirectView createUser(String username, String password, String dateOfBirth, String firstname, String lastname, String bio) throws ParseException {
        Date newDate = new SimpleDateFormat("yyyy-MM-dd").parse(dateOfBirth);
        ApplicationUser newUser = new ApplicationUser(username,encoder.encode(password), firstname, lastname,newDate, bio);
        appUserRepository.save(newUser);
        Authentication authentication = new UsernamePasswordAuthenticationToken(newUser, null, new ArrayList<>());
        SecurityContextHolder.getContext().setAuthentication(authentication);

        return new RedirectView("/");
    }


}
