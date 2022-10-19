package com.diksha.blog.demo.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
@Getter
@Setter
@Table(name = "categories")
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer categoryId;

    @Column(name = "title" ,length = 100,nullable = false)
    private String categoryTitle;

    @Column(name = "description")
    private String categoryDescription;

    //after creating post entity

    @OneToMany(mappedBy = "category" , cascade = CascadeType.ALL , fetch = FetchType.LAZY)
    private List<Post> posts = new ArrayList<>();

}
