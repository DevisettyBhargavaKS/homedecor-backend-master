package com.CodeWithBhargav.response;

import com.CodeWithBhargav.model.Address;
import com.CodeWithBhargav.model.OrderedProduct;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrderResponse {
    private Long id;
    private List<OrderedProduct> productList;
    private Long userId;
    private String name;
    private String username;
    private Address address;
    private String orderStatus;
}
