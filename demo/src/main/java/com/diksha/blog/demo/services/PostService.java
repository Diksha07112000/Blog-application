package com.diksha.blog.demo.services;

import com.diksha.blog.demo.entities.Post;
import com.diksha.blog.demo.payloads.PostDto;
import java.util.List;

public interface PostService {
    //create
    PostDto createPost(PostDto postDto, Integer userId , Integer categoryId);

    //update
    PostDto updatePost(PostDto postDto,Integer postId);

    //delete
    void deletePost(Integer postId);

    //get all posts
    List<Post> getAllPost();

    //get single post
    PostDto getPostById(Integer postId);

    //get all posts by category
    List<Post> getPostsByCategory(Integer categoryId);

    //get all posts by user
    List<Post> getPostsByUser(Integer userId);

    //search posts
    List<Post> searchPosts(String keyword);
}
