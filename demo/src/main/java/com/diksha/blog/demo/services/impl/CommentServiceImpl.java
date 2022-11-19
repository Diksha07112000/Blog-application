package com.diksha.blog.demo.services.impl;

import com.diksha.blog.demo.entities.Comment;
import com.diksha.blog.demo.entities.Post;
import com.diksha.blog.demo.entities.User;
import com.diksha.blog.demo.exceptions.ResourceNotFoundException;
import com.diksha.blog.demo.payloads.CommentDto;
import com.diksha.blog.demo.repositories.CommentRepo;
import com.diksha.blog.demo.repositories.PostRepo;
import com.diksha.blog.demo.repositories.UserRepo;
import com.diksha.blog.demo.services.CommentService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private CommentRepo commentRepo;

    @Autowired
    private PostRepo postRepo;

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public CommentDto createComment(CommentDto commentDto, Integer postId, Integer userId) {
        Post post = this.postRepo.findById(postId).orElseThrow(() -> new ResourceNotFoundException("Post", " post id ", postId));
        User user = this.userRepo.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User", " user id ", userId));
        Comment comment = this.modelMapper.map(commentDto, Comment.class);
        comment.setPost(post);
        comment.setUser(user);
        Comment createdComment = this.commentRepo.save(comment);
        return this.modelMapper.map(createdComment,CommentDto.class);
    }

    @Override
    public void deleteComment(Integer commentId) {
        Comment comment = this.commentRepo.findById(commentId).orElseThrow(() -> new ResourceNotFoundException("Comment", " comment id ", commentId));
        this.commentRepo.delete(comment);
    }
}