package com.diksha.blog.demo.payloads;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.*;

@NoArgsConstructor
@Getter
@Setter
public class UserDto {
    //fields which is taken input from user
    private int id;

    //add validators
//    @NotNull
    @NotEmpty //checks null and blank
    @Size(min = 4 , message = "Username must be min of 4 characters.")
    private String name;

    @Email(message = "Email address is not valid.")
    //check only @ sign , not .com pattern , we can use @pattern for that
    private String email;

//    @NotNull
    @NotEmpty
    @Size(min = 3 , max = 10, message = "Password must be 3-10 characters long !!!")
//  @Pattern(regexp = "anything")
    private String password;

    @NotEmpty
    private String about;
}
