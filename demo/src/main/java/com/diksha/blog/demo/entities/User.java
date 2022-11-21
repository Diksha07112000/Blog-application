package com.diksha.blog.demo.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.*;
import java.util.stream.Collectors;

@Entity
@Table(name = "users")
//use lombok for constructors
@NoArgsConstructor
@Getter
@Setter
public class User implements UserDetails {

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

    @OneToMany(mappedBy = "user" , cascade = CascadeType.ALL , fetch = FetchType.LAZY)
    private List<Post> posts = new ArrayList<>();

    @OneToMany(mappedBy = "user",cascade = CascadeType.ALL)
    private Set<Comment> comments = new HashSet<>();

    @ManyToMany(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    @JoinTable(name = "user_role",
            joinColumns = @JoinColumn(name = "user",referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "role",referencedColumnName = "id")
    )
    private Set<Role> roles = new HashSet<>();


    // Implemented UserDetails methods


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {

        //change each role to GrantedAuthority
        List<SimpleGrantedAuthority> authorities = this.roles.stream().map((role -> new SimpleGrantedAuthority(role.getName()))).collect(Collectors.toList());
        return authorities;
    }

    @Override
    public String getUsername() {
        return this.email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
