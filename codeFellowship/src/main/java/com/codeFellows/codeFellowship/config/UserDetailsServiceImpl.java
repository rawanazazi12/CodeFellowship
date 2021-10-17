package com.codeFellows.codeFellowship.config;

import com.codeFellows.codeFellowship.domain.ApplicationUser;
import com.codeFellows.codeFellowship.infrastructure.AppUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    AppUserRepository appUserRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        ApplicationUser user = appUserRepository.findUserByUsername(username);

        if (user == null){
            throw new UsernameNotFoundException(username + "Not Found");
        }
        return user;
    }

}