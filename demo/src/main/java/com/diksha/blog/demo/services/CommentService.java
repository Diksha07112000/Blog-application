package com.diksha.blog.demo.services;

import com.diksha.blog.demo.payloads.CommentDto;

public interface CommentService {

    //create
    CommentDto createComment(CommentDto commentDto, Integer postId, Integer userId);

    //delete
    void deleteComment(Integer commentId);
}
