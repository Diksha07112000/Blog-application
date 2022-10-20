package com.diksha.blog.demo.services.impl;

import com.diksha.blog.demo.entities.Category;
import com.diksha.blog.demo.entities.Post;
import com.diksha.blog.demo.entities.User;
import com.diksha.blog.demo.exceptions.ResourceNotFoundException;
import com.diksha.blog.demo.payloads.PostDto;
import com.diksha.blog.demo.payloads.PostResponse;
import com.diksha.blog.demo.repositories.CategoryRepo;
import com.diksha.blog.demo.repositories.PostRepo;
import com.diksha.blog.demo.repositories.UserRepo;
import com.diksha.blog.demo.services.PostService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

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

        Post post = this.postRepo.findById(postId).orElseThrow(() -> new ResourceNotFoundException("Post"," ID ",postId));
        post.setTitle(postDto.getTitle());
        post.setContent(postDto.getContent());
        post.setImageName(postDto.getImageName());
        Post updatedPost = this.postRepo.save(post);
        return this.modelMapper.map(updatedPost,PostDto.class);
    }

    @Override
    public void deletePost(Integer postId) {
        Post post = this.postRepo.findById(postId).orElseThrow(() -> new ResourceNotFoundException("Post", " ID ", postId));
        this.postRepo.delete(post);
    }

    @Override
    public PostResponse getAllPost(Integer pageNumber, Integer pageSize, String sortBy, String sortDir) {

        //for pagination
//        int pageSize = 5;
//        int pageNumber = 1;
        //third parameter for sorting

        Sort sort = null;
        if(sortDir.equalsIgnoreCase("asc"))
            sort = Sort.by(sortBy).ascending();
        else
            sort = Sort.by(sortBy).descending();

        Pageable p = PageRequest.of(pageNumber,pageSize,sort);
        //for sorting in descending order
        //Pageable p = PageRequest.of(pageNumber,pageSize,Sort.by(sortBy).descending());
        Page<Post> pagePosts = this.postRepo.findAll(p);

        //for getting all posts
        List<Post> allPosts = pagePosts.getContent();
//        List<Post> allPosts = this.postRepo.findAll();
        List<PostDto> getAllPosts = allPosts.stream().map(post -> this.modelMapper.map(post, PostDto.class)).collect(Collectors.toList());

        //generate postResponse

        PostResponse postResponse = new PostResponse();
        postResponse.setContent(getAllPosts);
        postResponse.setPageNumber(pagePosts.getNumber());
        postResponse.setPageSize(pagePosts.getSize());
        postResponse.setTotalElements(pagePosts.getNumberOfElements());
        postResponse.setTotalPages(pagePosts.getTotalPages());
        postResponse.setLastPage(pagePosts.isLast());
        return postResponse;
    }



    @Override
    public PostDto getPostById(Integer postId) {
        Post postById = this.postRepo.findById(postId).orElseThrow(() -> new ResourceNotFoundException("Post", " ID ", postId));
        return this.modelMapper.map(postById,PostDto.class);
    }

    @Override
    public List<PostDto> getPostsByCategory(Integer categoryId) {
        Category category = this.categoryRepo.findById(categoryId).orElseThrow(() -> new ResourceNotFoundException("Category", " ID ", categoryId));
        List<Post> posts = this.postRepo.findByCategory(category);
        List<PostDto> postsByCategory = posts.stream().map(post -> this.modelMapper.map(post, PostDto.class)).collect(Collectors.toList());
        return postsByCategory;
    }

    @Override
    public List<PostDto> getPostsByUser(Integer userId) {
        User user = this.userRepo.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User", " ID ", userId));
        List<Post> posts = this.postRepo.findByUser(user);
        List<PostDto> postsByUser = posts.stream().map(post -> this.modelMapper.map(post, PostDto.class)).collect(Collectors.toList());
        return postsByUser;
    }

    @Override
    public List<PostDto> searchPosts(String keyword) {
        return null;
    }


}
