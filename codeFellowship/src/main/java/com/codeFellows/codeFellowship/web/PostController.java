package com.codeFellows.codeFellowship.web;

import com.codeFellows.codeFellowship.domain.ApplicationUser;
import com.codeFellows.codeFellowship.domain.Post;
import com.codeFellows.codeFellowship.infrastructure.AppUserRepository;
import com.codeFellows.codeFellowship.infrastructure.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.view.RedirectView;

import java.security.Principal;
import java.sql.Date;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Set;

@Controller
public class PostController {

    @Autowired
    AppUserRepository appUserRepository;

    @Autowired
    PostRepository postRepository;


    @PostMapping("/posts")
    public RedirectView createPost(Principal principal, Model model, String body) throws ParseException {
        ApplicationUser user = appUserRepository.findUserByUsername(principal.getName());

        if(user != null){
            Timestamp timestamp = new Timestamp(System.currentTimeMillis());
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss");
            sdf.format(timestamp);
            Post post = new Post(body, timestamp, user);
            postRepository.save(post);
        }

        model.addAttribute("principal", principal.getName());
        model.addAttribute("appUser", user);
        assert user != null;
        model.addAttribute("posts" , user.getPosts());
        return new RedirectView("/myProfile");
    }

    @GetMapping("/post")
    public String getPostPage(Model model,Principal principal) {
        ApplicationUser appUser =  appUserRepository.findUserByUsername(principal.getName());
        model.addAttribute("appUser",appUser);

        return "posts";
    }

    @GetMapping("/feed")
    public String getFollowingsPosts(Model model){
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        ApplicationUser appUser = appUserRepository.findUserByUsername(userDetails.getUsername());
        Set<ApplicationUser> following = appUser.getFollowing();
//        Set posts = (Set) postRepository.findByAppUserIn(following);

        model.addAttribute("appUser",appUser);
//        model.addAttribute("principal",principal.getName());
        model.addAttribute("following",following);

        return "feed";
    }

    @GetMapping("/edit/{id}")
    public String showUpdateForm(@PathVariable long id, Model model, Principal principal) {
        ApplicationUser loggedInUser = appUserRepository.findUserByUsername(principal.getName());
//        long newId = Long.parseLong(id);
        Post post = (Post) postRepository.findPostById(id).orElseThrow();
         loggedInUser.getPosts();
        model.addAttribute("post", post);
        return "update-post";
    }

    @PostMapping("/update/{id}")
    public RedirectView updateUser(@PathVariable long id,
                             BindingResult result, Model model) {

//        long newId = Long.parseLong(id);
        Post post = (Post) postRepository.findPostById(id).orElseThrow();

        if (result.hasErrors()) {

            post.setId(id);
            return new RedirectView("/update-post") ;
        }
        model.addAttribute("post", post);
        postRepository.save(post);
        return new RedirectView("/myProfile");
    }

}
