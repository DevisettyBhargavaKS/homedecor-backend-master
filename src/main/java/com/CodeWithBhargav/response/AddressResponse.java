package com.CodeWithBhargav.response;

import com.CodeWithBhargav.model.Address;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class AddressResponse {

    private List<Address> addressList;
}
