package com.CodeWithBhargav.controller.Admin;

import com.CodeWithBhargav.model.OrderStatus;
import com.CodeWithBhargav.model.Role;
import com.CodeWithBhargav.request.OrderStatusRequest;
import com.CodeWithBhargav.response.OrderResponse;
import com.CodeWithBhargav.response.common.APIResponse;
import com.CodeWithBhargav.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;
import java.util.List;

@RestController
@RequestMapping("/api/admin/order")
//@PreAuthorize("hasRole('ROLE_ADMIN')")
@RolesAllowed(Role.ADMIN)
public class AdminOrderController {

    @Autowired
    private APIResponse apiResponse;

    @Autowired
    private OrderService orderService;

    @GetMapping("/all")
    public ResponseEntity<APIResponse> getAllOrders() {
        List<OrderResponse> orderList = orderService.getAllOrders();
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setData(orderList);
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<APIResponse> getUsersOrder(@PathVariable Long userId) {
        List<OrderResponse> orderList = orderService.getUserOrders(userId);
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setData(orderList);
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

    @GetMapping("/status/all")
    public ResponseEntity<APIResponse> getAllStatus() {
        List<OrderStatus> orderList = orderService.getAllOrderStatus();
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setData(orderList);
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

    @PutMapping("/status")
    public ResponseEntity<APIResponse> updateOrderStatus(
            @RequestBody OrderStatusRequest orderStatusRequest) {
        List<OrderResponse> orderList = orderService
                .updateOrderStatus(orderStatusRequest.getOrderId(),
                        orderStatusRequest.getStatusId());
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setData(orderList);
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }



}
