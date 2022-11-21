package com.diksha.blog.demo.repositories;

import com.diksha.blog.demo.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepo extends JpaRepository<User,Integer> {

    //for security -> used in CustomUserDetailService
    Optional<User> findByEmail(String email);

}

//after this , make services
