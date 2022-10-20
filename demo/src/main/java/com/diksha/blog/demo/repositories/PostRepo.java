package com.diksha.blog.demo.repositories;

import com.diksha.blog.demo.entities.Category;
import com.diksha.blog.demo.entities.Post;
import com.diksha.blog.demo.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepo extends JpaRepository<Post,Integer> {
    //to get all the post from a user
    List<Post> findByUser(User user);

    //to get all the post from a category
    List<Post> findByCategory(Category category);

    List<Post> findByTitleContaining(String title);

    //since , JPARepository implements paging and sorting, we can directly implement paging and sorting by passing parameters in url.


}
