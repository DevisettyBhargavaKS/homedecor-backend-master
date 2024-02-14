package com.CodeWithBhargav.request;

import lombok.*;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class AddressRequest {
    private Long id;
    private Long userId;
    @NotEmpty
    @Size(min = 4, message = "address should have at least 4 characters")
    private String address;
    @NotEmpty
    @Size(min = 4, message = "city should have at least 4 characters")
    private String city;
    @NotEmpty
    @Size(min = 4, message = "zipcode should have at least 4 characters")
    private Integer zipcode;
}
