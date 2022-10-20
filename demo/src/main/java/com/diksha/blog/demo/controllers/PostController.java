package com.diksha.blog.demo.controllers;

import com.diksha.blog.demo.payloads.ApiResponse;
import com.diksha.blog.demo.payloads.PostDto;
import com.diksha.blog.demo.payloads.PostResponse;
import com.diksha.blog.demo.services.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.Inet4Address;
import java.util.List;

@RestController
@RequestMapping("/api/")
public class PostController {

    @Autowired
    private PostService postService;

    //create post
    @PostMapping("/user/{userId}/category/{categoryId}/posts")
    public ResponseEntity<PostDto> createPost(@RequestBody PostDto postDto , @PathVariable Integer userId , @PathVariable Integer categoryId){
        PostDto createdPost = this.postService.createPost(postDto, userId, categoryId);
        return new ResponseEntity<PostDto>(createdPost, HttpStatus.CREATED);
    }

    //get all post by category
    @GetMapping("/category/{categoryId}/posts")
    public ResponseEntity<List<PostDto>> getByCategory(@PathVariable Integer categoryId){
        List<PostDto> postsByCategory = this.postService.getPostsByCategory(categoryId);
        return new ResponseEntity<List<PostDto>>(postsByCategory,HttpStatus.OK);
    }
    //get all post by user
    @GetMapping("/user/{userId}/posts")
    public ResponseEntity<List<PostDto>> getByUser(@PathVariable Integer userId){
        List<PostDto> postsByUser = this.postService.getPostsByUser(userId);
        return new ResponseEntity<List<PostDto>>(postsByUser,HttpStatus.OK);
    }

    //get all posts
    @GetMapping("/posts")
    public ResponseEntity<PostResponse> getAllPosts(
            //for pagination
            //postResponse should contain pageNumber,pageSize,totalElements,totalpages, lastpage,Content
            //make PostResponse ->payload

            @RequestParam(value = "pageNumber",defaultValue = "0",required = false) Integer pageNumber,
            @RequestParam(value = "pageSize",defaultValue = "10",required = false) Integer pageSize,
            @RequestParam(value = "sortBy",defaultValue = "postId",required = false) String sortBy,
            @RequestParam(value = "sortDir",defaultValue = "asc",required = false) String sortDir
            ){
        PostResponse postResponse = this.postService.getAllPost(pageNumber, pageSize,sortBy,sortDir);
        return new ResponseEntity<PostResponse>(postResponse,HttpStatus.OK);
    }

    // get post by ID
    @GetMapping("/posts/{postId}")
    public ResponseEntity<PostDto> getPostById(@PathVariable Integer postId){
        PostDto postById = this.postService.getPostById(postId);
        return new ResponseEntity<PostDto>(postById,HttpStatus.OK);
    }

    //update post
    @PutMapping("/posts/{postId}")
    public ResponseEntity<PostDto> updatePost(@RequestBody PostDto postDto ,@PathVariable Integer postId){
        PostDto updatedPost = this.postService.updatePost(postDto,postId);
        return new ResponseEntity<PostDto>(updatedPost, HttpStatus.CREATED);
    }

    //Delete post
    @DeleteMapping("/posts/{postId}")
    public ApiResponse deletePost(@PathVariable Integer postId){
        this.postService.deletePost(postId);
        return new ApiResponse("Post Deleted successfully",true);
    }

    @GetMapping("posts/search/{keyword}")
    public ResponseEntity<List<PostDto>> searchPost(@PathVariable("keyword") String keyword){
        List<PostDto> searchedPosts = this.postService.searchPosts(keyword);
        return new ResponseEntity<List<PostDto>>(searchedPosts,HttpStatus.OK);
    }
}
