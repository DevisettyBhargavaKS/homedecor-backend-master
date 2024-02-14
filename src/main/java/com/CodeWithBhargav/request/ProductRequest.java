package com.CodeWithBhargav.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductRequest {
    private Long id;
    private Long category_id;
    private Long vendor_id;
    @NotEmpty
    @Size(min = 4, message = "title should have at least 4 characters")
    private String title;
    @NotEmpty
    @Size(min = 4, message = "description should have at least 4 characters")
    private String description;
    @NotEmpty
    @Size(min = 1, message = "price should have at least 1 digit")
    private Double price;
    @Column
    private String photo;
}
