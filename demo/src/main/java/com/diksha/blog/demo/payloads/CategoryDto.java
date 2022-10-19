package com.diksha.blog.demo.payloads;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@NoArgsConstructor
@Getter
@Setter
public class CategoryDto {

    private Integer categoryId;

    @NotEmpty
    @Size(min = 4,message = "Min size for title is 4")
    private String categoryTitle;

    @NotEmpty
    @Size(min = 10,message = "Min size for title is 10")
    private String categoryDescription;

}
