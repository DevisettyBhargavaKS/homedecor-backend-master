package com.CodeWithBhargav.dto;

import com.CodeWithBhargav.model.Address;
import com.CodeWithBhargav.request.AddressRequest;
import com.CodeWithBhargav.response.AddressResponse;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
public class AddressDto {
    public AddressResponse mapToAddressResponse(List<Address> addressList) {
        return new AddressResponse(addressList);

    }

    public Address mapToAddress(AddressRequest addressRequest) {
        Address address = new Address();
        if (addressRequest.getId() != null) {
            address.setId(addressRequest.getId());
        }
        address.setAddress(addressRequest.getAddress());
        address.setCity(addressRequest.getCity());
        address.setZipcode(addressRequest.getZipcode());
        return address;
    }

    }

