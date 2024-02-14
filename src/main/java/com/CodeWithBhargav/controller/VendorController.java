package com.CodeWithBhargav.controller;

import com.CodeWithBhargav.model.Product;
import com.CodeWithBhargav.model.Role;
import com.CodeWithBhargav.model.Vendor;
import com.CodeWithBhargav.request.ProductRequest;
import com.CodeWithBhargav.request.VendorRequest;
import com.CodeWithBhargav.response.ProductResponse;
import com.CodeWithBhargav.response.VendorResponse;
import com.CodeWithBhargav.response.common.APIResponse;
import com.CodeWithBhargav.service.ProductService;
import com.CodeWithBhargav.service.StorageService;
import com.CodeWithBhargav.service.VendorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.security.RolesAllowed;
import javax.validation.Valid;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/vendor")
//@PreAuthorize("hasRole('ROLE_ADMIN')")
@RolesAllowed({Role.ADMIN, Role.VENDOR})
public class VendorController {
    @Autowired
    private APIResponse apiResponse;

    @Autowired
    private ProductService productService;

    @Autowired
    private VendorService vendorService;
    @Autowired
    private StorageService storageService;

    @GetMapping("/all/all")
    public ResponseEntity<APIResponse> getAllVendorProducts(){
        List<Product>  productList = productService.findAll();
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setData(productList);
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

   @PostMapping( consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
  public ResponseEntity<APIResponse> createProduct(
          @RequestParam("photo") MultipartFile photo,
          @RequestParam("categoryId") Long category_id,
          @RequestParam("title") String title,
          @RequestParam("description") String description,
          @RequestParam("price") double  price)throws IOException{
       String file = storageService.storeFile(photo);
       ProductRequest productRequest=new ProductRequest();
       productRequest.setTitle(title);
       productRequest.setDescription(description);
       productRequest.setPhoto(file);
       productRequest.setCategory_id(category_id);
       productRequest.setPrice(price);

       List<Product> bookList = productService.createProduct(productRequest);
       apiResponse.setStatus(HttpStatus.OK.value());
       apiResponse.setData(bookList);
       return new ResponseEntity<>(apiResponse, HttpStatus.OK);


   }


    @PutMapping
    public ResponseEntity<APIResponse> updateProduct(@RequestBody
                                                         ProductRequest productRequest) {
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
