package com.diksha.blog.demo.security;

import com.diksha.blog.demo.entities.User;
import com.diksha.blog.demo.exceptions.ResourceNotFoundStringException;
import com.diksha.blog.demo.repositories.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailService implements UserDetailsService {

    @Autowired
    private UserRepo userRepo;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //loading user from database by username
        User user = this.userRepo.findByEmail(username).orElseThrow(() -> new ResourceNotFoundStringException("User", " email ", username));

        // now we need to return UserDetails but we have user , so in user.java let user implement UserDetails

        return user;
    }
}
