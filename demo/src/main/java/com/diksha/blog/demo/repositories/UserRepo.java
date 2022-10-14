package com.diksha.blog.demo.repositories;

import com.diksha.blog.demo.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<User,Integer> {
}
