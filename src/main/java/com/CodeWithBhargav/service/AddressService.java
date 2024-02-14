package com.CodeWithBhargav.service;

import com.CodeWithBhargav.dto.AddressDto;
import com.CodeWithBhargav.exception.common.ResourceNotFoundException;
import com.CodeWithBhargav.model.Address;
import com.CodeWithBhargav.model.AppUser;
import com.CodeWithBhargav.repository.AddressRepository;
import com.CodeWithBhargav.repository.UserRepository;
import com.CodeWithBhargav.request.AddressRequest;
import com.CodeWithBhargav.response.AddressResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class AddressService {

    @Autowired
    private AddressRepository addressRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private AddressDto addressDto;

    public AddressResponse findAll() {
        List<Address> addressList = addressRepository.findAll();
        return addressDto.mapToAddressResponse(addressList);
    }

    @Transactional
    public AddressResponse create(AddressRequest addressRequest) {
        Address address = addressDto.mapToAddress(addressRequest);
        AppUser appUser = userRepository.findById(addressRequest.getUserId())
                .orElseThrow(() -> new ResourceNotFoundException("userId", "userId",
                        addressRequest.getUserId()));
        address.setAppUser(appUser);
        addressRepository.save(address);
        return findAll();
    }

    public AddressResponse update(AddressRequest addressRequest) {
        Address address = addressDto.mapToAddress(addressRequest);
        AppUser appUser = userRepository.findById(addressRequest.getUserId())
                .orElseThrow(() -> new ResourceNotFoundException("userId", "userId",
                        addressRequest.getUserId()));
        address.setAppUser(appUser);
        addressRepository.save(address);
        return findAll();
    }

    public AddressResponse deleteById(Long id) {
        addressRepository.deleteById(id);
        return findAll();
    }
}
