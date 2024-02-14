package com.CodeWithBhargav.dto;

import com.CodeWithBhargav.model.Vendor;
import com.CodeWithBhargav.request.VendorRequest;
import com.CodeWithBhargav.response.VendorResponse;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
@Component
public class VendorDto {
    public VendorResponse mapToVendorResponse(List<Vendor> vendors) {
        VendorResponse vendorResponse = new VendorResponse();

        ArrayList<VendorRequest> vendorRequests = new ArrayList<>();
        for (Vendor vendor : vendors){
//            vendorRequests.add(new VendorRequest(vendor.getId(), vendor.getUsername()));
        }
        vendorResponse.setVendors(vendorRequests);
        return vendorResponse;
    }
    public Vendor mapToVendor(VendorRequest vendorRequest) {
        Vendor vendor = new Vendor();
        if (vendorRequest.getId() != null) {
            vendor.setId(vendorRequest.getId());
        }
        vendor.setUsername(vendorRequest.getUsername());
        vendor.setTitle(vendorRequest.getTitle());

        return vendor;
    }
}
