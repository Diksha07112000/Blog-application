package com.diksha.blog.demo.payloads;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class UserDto {
    //fields which is taken input from user
    private int id;

    private String name;

    private String email;

    private String password;

    private String about;
}
