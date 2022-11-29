package com.diksha.blog.demo.payloads;

import lombok.Data;

@Data
public class JwtAuthRequest {
    private String username;
    private String password;
}
