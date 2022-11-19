package com.diksha.blog.demo.repositories;

import com.diksha.blog.demo.entities.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepo extends JpaRepository<Comment,Integer> {
}
