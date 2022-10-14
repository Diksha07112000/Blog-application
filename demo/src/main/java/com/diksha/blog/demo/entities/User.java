package com.diksha.blog.demo.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "users")
//use lombok for constructors
@NoArgsConstructor
@Getter
@Setter
public class User {

    @Id
    //for auto incrementing id value
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    //for changing name of column in table use column annotation
    //nullable=false means not null
    @Column(name = "user_name",nullable = false,length = 100)
    private String name;

    private String email;

    private String password;

    private String about;

    //after this , user ka data fetch krne k lie ya dalne k lie repository create krna hoga -> repositories
}
