package com.CodeWithBhargav.dto;

import com.CodeWithBhargav.model.Product;
import com.CodeWithBhargav.request.ProductRequest;
import org.springframework.stereotype.Component;

@Component
public class ProductDto {
    public static Product mapToProduct(ProductRequest productRequest) {
        Product product = new Product();
        if (productRequest.getId() != null) {
            product.setId(productRequest.getId());
        }
        product.setPrice(productRequest.getPrice());
        product.setDescription(productRequest.getDescription());
        product.setTitle(productRequest.getTitle());
        product.setPhoto(productRequest.getPhoto());
        return product;

    }
}
