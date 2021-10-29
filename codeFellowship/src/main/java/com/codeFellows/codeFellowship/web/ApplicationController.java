package com.codeFellows.codeFellowship.web;
import com.codeFellows.codeFellowship.domain.ApplicationUser;
import com.codeFellows.codeFellowship.domain.Post;
import com.codeFellows.codeFellowship.infrastructure.AppUserRepository;
import com.codeFellows.codeFellowship.infrastructure.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.view.RedirectView;

import java.security.Principal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

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
    public String getLoginPage(Model model) {
        if(SecurityContextHolder.getContext().getAuthentication().getPrincipal().getClass() == ApplicationUser.class){

            ApplicationUser user = (ApplicationUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            model.addAttribute("appUser",user);
        }

        return "/login";
    }

    @PostMapping("/signup")
    public RedirectView createUser(@RequestParam (value = "username") String username,
                                    @RequestParam (value = "password") String password,
                                    @RequestParam (value = "dateOfBirth") String dateOfBirth,
                                    @RequestParam (value = "firstName") String firstName,
                                    @RequestParam (value = "lastName") String lastName,
                                    @RequestParam (value = "bio") String bio) throws ParseException {
        Date newDate = new SimpleDateFormat("yyyy-MM-dd").parse(dateOfBirth);
        ApplicationUser newUser = new ApplicationUser(username,encoder.encode(password), firstName, lastName,newDate, bio);
        appUserRepository.save(newUser);
        Authentication authentication = new UsernamePasswordAuthenticationToken(newUser, null, new ArrayList<>());
        SecurityContextHolder.getContext().setAuthentication(authentication);

        return new RedirectView("/login");
    }

    @GetMapping("/users")
    public String getUsersPage(Principal principal, Model model) {
        ApplicationUser appUser = appUserRepository.findUserByUsername(principal.getName());
        List<ApplicationUser> users = appUserRepository.findAll();
        model.addAttribute("appUser",appUser);
        model.addAttribute("principal", principal.getName());
        model.addAttribute("users", users);
        return "users";
    }

    @GetMapping("/myProfile")
    public String getMyProfilePage(Principal principal, Model model) {
        ApplicationUser appUser = appUserRepository.findUserByUsername(principal.getName());
        List <Post> posts = postRepository.findAllById(appUser.getId()).orElseThrow();
        model.addAttribute("appUser",appUser);
        model.addAttribute("principal", principal.getName());
        model.addAttribute("posts",posts);
        return "myProfile";
    }

    @GetMapping("/users/{id}")
    public String getSingleAppUserPage(Model model, Principal principal, @PathVariable Long id) {
       ApplicationUser appUser = appUserRepository.findUserById (id);
        model.addAttribute("appUser", appUser);
        model.addAttribute("principal", principal.getName());
        return "oneUser";
    }

    @GetMapping("/following")
    public String getFollowingPage(Principal principal, Model model) {
        ApplicationUser appUser = appUserRepository.findUserByUsername(principal.getName());
        Iterable<ApplicationUser> users = appUser.getFollowing();
        model.addAttribute("appUser",appUser);
        model.addAttribute("principal", principal.getName());
        model.addAttribute("users", users);
        return "following";
    }


    @PostMapping("/follow/{id}")
    public RedirectView followUser(Principal principal, @PathVariable long id) throws ParseException {

        ApplicationUser loggedInUser = appUserRepository.findUserByUsername(principal.getName());
        ApplicationUser userToFollow = appUserRepository.findUserById(id);

        loggedInUser.getFollowing().add(userToFollow);
        userToFollow.getFollowers().add(loggedInUser);
        appUserRepository.save(loggedInUser);
        appUserRepository.save(userToFollow);

        return new RedirectView("/users");
    }


}
