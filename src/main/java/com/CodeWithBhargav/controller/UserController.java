package com.CodeWithBhargav.controller;

import com.CodeWithBhargav.model.AppUser;
import com.CodeWithBhargav.model.Role;
import com.CodeWithBhargav.request.AddressRequest;
import com.CodeWithBhargav.response.AddressResponse;
import com.CodeWithBhargav.response.common.APIResponse;
import com.CodeWithBhargav.service.AddressService;
import com.CodeWithBhargav.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;

@RestController
@RequestMapping("/api/user")
//@PreAuthorize("hasRole('ROLE_USER')")
@RolesAllowed(Role.USER)
public class UserController {

    @Autowired
    private APIResponse apiResponse;
    @Autowired
    private UserService userService;
    @Autowired
    private AddressService addressService;

    @GetMapping("/{userId}")
    public ResponseEntity<APIResponse> getUserDetails(@PathVariable Integer userId) {
        AppUser appUser = userService.findUserById(Long.valueOf(userId));
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setData(appUser);
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

    @PostMapping("/address")
    public ResponseEntity<APIResponse> createAddress(@RequestBody
                                                     AddressRequest addressRequest) {
        AddressResponse addressResponse = addressService.create(addressRequest);
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setData(addressResponse.getAddressList());
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

    @PutMapping("/address")
    public ResponseEntity<APIResponse> updateAddress(@RequestBody
                                                     AddressRequest addressRequest) {
        AddressResponse addressResponse = addressService.update(addressRequest);
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setData(addressResponse.getAddressList());
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

    @DeleteMapping("/address/{id}")
    public ResponseEntity<APIResponse> deleteAddress(@PathVariable Integer id) {
        AddressResponse addressResponse = addressService
                .deleteById(Long.valueOf(id));
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setData(addressResponse.getAddressList());
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }
}
