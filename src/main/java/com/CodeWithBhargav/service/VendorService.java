package com.CodeWithBhargav.service;
import com.CodeWithBhargav.dto.VendorDto;
import com.CodeWithBhargav.model.AppUser;
import com.CodeWithBhargav.model.Vendor;
import com.CodeWithBhargav.repository.ProductRepository;
import com.CodeWithBhargav.repository.VendorRepository;
import com.CodeWithBhargav.request.ProductRequest;
import com.CodeWithBhargav.request.VendorRequest;
import com.CodeWithBhargav.response.VendorResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import java.util.List;

@Service
public class VendorService {

    @Autowired
    private VendorRepository vendorRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private UserService userService;
    @Autowired
    private VendorDto vendorDto;

    public VendorResponse findAll() {
        List<Vendor> vendors = vendorRepository.findAll();
        return vendorDto.mapToVendorResponse(vendors);
    }

    public VendorResponse create(VendorRequest vendorRequest) {
        Vendor vendor = vendorDto.mapToVendor(vendorRequest);
//        AppUser appUser = userService.findUserById(vendorRequest.getAppUserid());
//        vendor.setAppUser(appUser);
        vendorRepository.save(vendor);
        return findAll();
    }

    public VendorResponse update(@Valid VendorRequest vendorRequest) {
        Vendor vendor  = vendorDto.mapToVendor(vendorRequest);
        vendorRepository.save(vendor);
        return findAll();
    }

    public VendorResponse deleteById(Integer id) {
        vendorRepository.deleteById(Long.valueOf(id));
        return findAll();
    }

    public List<Vendor> findAllVendors() {
        return vendorRepository.findAll();
    }

    public Vendor createVendor(VendorRequest vendorRequest) {
        Vendor vendor = vendorDto.mapToVendor(vendorRequest);
        AppUser appUser=userService.findUserById(vendorRequest.getUser_id());

        vendor.setAppUser(appUser);
        vendorRepository.save(vendor);
        return vendor;
    }

}
