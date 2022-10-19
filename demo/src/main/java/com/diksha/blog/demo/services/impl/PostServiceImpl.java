package com.diksha.blog.demo.services.impl;

import com.diksha.blog.demo.entities.Category;
import com.diksha.blog.demo.entities.Post;
import com.diksha.blog.demo.entities.User;
import com.diksha.blog.demo.exceptions.ResourceNotFoundException;
import com.diksha.blog.demo.payloads.PostDto;
import com.diksha.blog.demo.repositories.CategoryRepo;
import com.diksha.blog.demo.repositories.PostRepo;
import com.diksha.blog.demo.repositories.UserRepo;
import com.diksha.blog.demo.services.PostService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class PostServiceImpl implements PostService {

    @Autowired
    private PostRepo postRepo;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private CategoryRepo categoryRepo;


    @Override
    public PostDto createPost(PostDto postDto, Integer userId , Integer categoryId) {

        User user = this.userRepo.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User"," ID ",userId));

        Category category = this.categoryRepo.findById(categoryId).orElseThrow(() -> new ResourceNotFoundException("Category"," ID ",categoryId));

        Post post = this.modelMapper.map(postDto, Post.class);
        post.setImageName("default.png");
        post.setPostDate(new Date());
        post.setUser(user);
        post.setCategory(category);
        Post createdPost = this.postRepo.save(post);
        return this.modelMapper.map(createdPost,PostDto.class);
    }

    @Override
    public PostDto updatePost(PostDto postDto, Integer postId) {
        return null;
    }

    @Override
    public void deletePost(Integer postId) {

    }

    @Override
    public List<Post> getAllPost() {
        return null;
    }

    @Override
    public PostDto getPostById(Integer postId) {
        return null;
    }

    @Override
    public List<Post> getPostsByCategory(Integer categoryId) {
        return null;
    }

    @Override
    public List<Post> getPostsByUser(Integer userId) {
        return null;
    }

    @Override
    public List<Post> searchPosts(String keyword) {
        return null;
    }
}
