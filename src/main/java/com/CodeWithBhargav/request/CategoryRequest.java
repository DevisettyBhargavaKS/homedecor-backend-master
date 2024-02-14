package com.CodeWithBhargav.request;

import lombok.*;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class CategoryRequest  {
    private Long id;

    public CategoryRequest(String title) {
       this.title = title;
    }

    @NotEmpty
    @Size(min = 4, message = "title should have at least 4 characters")
    private String title;
}
