package com.diksha.blog.demo.services;

import com.diksha.blog.demo.payloads.UserDto;

import java.util.List;

// don't pass entities directly to services , instead use data transfer objects(dto)
public interface UserService {

    UserDto createUser(UserDto user);

    UserDto updateUser(UserDto user,Integer userId);

    UserDto getUserById(Integer userId);

    List<UserDto> getAllUsers();

    void deleteUser(Integer userId);
}
