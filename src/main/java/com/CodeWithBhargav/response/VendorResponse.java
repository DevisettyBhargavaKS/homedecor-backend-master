package com.CodeWithBhargav.response;
import com.CodeWithBhargav.request.VendorRequest;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@ToString
public class VendorResponse {
    private List<VendorRequest> vendors = new ArrayList<>();


}
