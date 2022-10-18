package com.diksha.blog.demo.repositories;

import com.diksha.blog.demo.entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepo extends JpaRepository<Category,Integer> {
}
