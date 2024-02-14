package com.CodeWithBhargav.response;

import com.CodeWithBhargav.model.Product;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class ProductResponse {
    private List<Product> productList;



}
