package com.diksha.blog.demo.controllers;

import com.diksha.blog.demo.payloads.ApiResponse;
import com.diksha.blog.demo.payloads.UserDto;
import com.diksha.blog.demo.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

//make apis for all methods

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;


    //Post- create user

//    @RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    @PostMapping("/")
    public ResponseEntity<UserDto> createUser(@RequestBody UserDto userDto)
    {
        UserDto createdUserDto = this.userService.createUser(userDto);
        return new ResponseEntity<>(createdUserDto, HttpStatus.CREATED);
    }
    //Put-update user
    @PutMapping("/{userId}")
    public ResponseEntity<UserDto> updateUser(@RequestBody UserDto userDto,@PathVariable("userId") Integer uid)
    {
        UserDto updatedUserDto = this.userService.updateUser(userDto, uid);
        return ResponseEntity.ok(updatedUserDto);
    }


    //Delete-delete user

    @DeleteMapping("/{userId}")
    public ResponseEntity<ApiResponse> deleteUser(@PathVariable("userId") Integer uid)
    {
        this.userService.deleteUser(uid);
        //1) for sending a message without status code
        // return ResponseEntity.ok(Map.of("message","User Deleted Successfully"));
        //2) for sending message with status code
        // return new ResponseEntity(Map.of("message","User Deleted Successfully"),HttpStatus.OK);

        //3) we are creating separate class for ApiResponse
        return new ResponseEntity<ApiResponse>(new ApiResponse("User Deleted",true),HttpStatus.OK);

    }
    //GetAll - get all users

    @GetMapping("/")
    public ResponseEntity<List<UserDto>> getAllUsers(){
        return ResponseEntity.ok(this.userService.getAllUsers());
    }

    //Get Single user

    @GetMapping("/{userId}")
    public ResponseEntity<UserDto> getUserById(@PathVariable Integer userId){
        return ResponseEntity.ok(this.userService.getUserById(userId));
    }
 
}
