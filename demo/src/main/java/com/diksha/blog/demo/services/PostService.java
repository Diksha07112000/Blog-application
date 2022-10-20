package com.diksha.blog.demo.services;

import com.diksha.blog.demo.entities.Post;
import com.diksha.blog.demo.payloads.PostDto;
import com.diksha.blog.demo.payloads.PostResponse;

import java.util.List;

public interface PostService {
    //create
    PostDto createPost(PostDto postDto, Integer userId , Integer categoryId);

    //update
    PostDto updatePost(PostDto postDto,Integer postId);

    //delete
    void deletePost(Integer postId);

    //get all posts
    //List<PostDto> getAllPost(Integer pageNumber,Integer pageSize);
    PostResponse getAllPost(Integer pageNumber, Integer pageSize);

    //get single post
    PostDto getPostById(Integer postId);

    //get all posts by category
    List<PostDto> getPostsByCategory(Integer categoryId);

    //get all posts by user
    List<PostDto> getPostsByUser(Integer userId);

    //search posts
    List<PostDto> searchPosts(String keyword);
}
