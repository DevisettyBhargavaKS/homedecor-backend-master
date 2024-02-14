package com.CodeWithBhargav.controller.Admin;

import com.CodeWithBhargav.model.Product;
import com.CodeWithBhargav.model.Role;
import com.CodeWithBhargav.request.ProductRequest;
import com.CodeWithBhargav.response.common.APIResponse;
import com.CodeWithBhargav.service.ProductService;
import com.CodeWithBhargav.service.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.security.RolesAllowed;
import java.util.List;

@RestController
@RequestMapping("/api/admin/product")
//@PreAuthorize("hasRole('ROLE_ADMIN')")
@RolesAllowed(Role.ADMIN)
public class AdminProductController {

    @Autowired
    private APIResponse apiResponse;

    @Autowired
    private StorageService storageService;

    @Autowired
    private ProductService productService;

    @GetMapping("/all")
    public ResponseEntity<APIResponse> getAllProducts() {
        List<Product> productList = productService.findAll();
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setData(productList);
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<APIResponse> createProduct(@PathVariable Long category_id,@PathVariable Long vendor_id, @PathVariable String title,@PathVariable String description, @PathVariable Double price, @RequestParam("photo") MultipartFile photo) {
        String file = storageService.storeFile(photo);
        ProductRequest productRequest = new ProductRequest();
        productRequest.setTitle(title);
        productRequest.setDescription(description);
        productRequest.setCategory_id(category_id);
        productRequest.setVendor_id(vendor_id);
        productRequest.setPrice(price);
        productRequest.setPhoto(file);
        List<Product> productList = productService.createProduct(productRequest);
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setData(productList);
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<APIResponse> updateProduct(@RequestBody ProductRequest productRequest

    ) {
        List<Product> productList = productService.updateProduct(productRequest);
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setData(productList);
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<APIResponse> deleteProduct(@PathVariable Long id) {
        List<Product> productList = productService.deleteById(id);
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setData(productList);
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

}
