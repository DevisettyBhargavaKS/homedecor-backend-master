package com.CodeWithBhargav.request;

import lombok.*;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString

public class    VendorRequest {
    private Long id;
    private Long user_id;

    @NotEmpty
   @Size(min = 3, message = "name should have at least 3 characters")
    private String username;

    @NotEmpty
    @Size(min = 3, message = "title should have at least 3 characters")
    private String title;



}
