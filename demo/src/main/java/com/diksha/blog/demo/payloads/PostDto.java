package com.diksha.blog.demo.payloads;

import com.diksha.blog.demo.entities.Category;
import com.diksha.blog.demo.entities.Comment;
import com.diksha.blog.demo.entities.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@NoArgsConstructor
@Getter
@Setter
public class PostDto {

    private Integer postId;

    private String title;

    private String content;

    private String imageName;

    private Date postDate;

    //we will use dto, otherwise it will go into infinite loop(category->post->category->post....)
    private CategoryDto category;

    private UserDto user;

    //to see comments of a post from APIs of post entity
    //if we're making GET api for comment then there is no need to add this statement.
    private Set<CommentDto> comments = new HashSet<>();

}
