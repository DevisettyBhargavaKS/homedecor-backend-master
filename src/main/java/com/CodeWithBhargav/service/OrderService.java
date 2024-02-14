package com.CodeWithBhargav.service;

import com.CodeWithBhargav.dto.OrderDto;
import com.CodeWithBhargav.exception.common.ResourceNotFoundException;
import com.CodeWithBhargav.model.*;
import com.CodeWithBhargav.repository.*;
import com.CodeWithBhargav.request.OrderRequest;
import com.CodeWithBhargav.response.OrderResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class OrderService {
    @Autowired
    private OrderDto orderDto;
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private CartRepository cartRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserService userService;
    @Autowired
    private OrderedProductRepository orderedProductRepository;
    @Autowired
    private AddressRepository addressRepository;
    @Autowired
    private OrderStatusRepository orderStatusRepository;

    @Transactional
    public List<OrderResponse> placeOrder(Long userId, Long addressId) {
        AppUser appUser = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("userId", "userId", userId));

        Address address = addressRepository.findById(addressId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("addressId", "addressId", addressId));

        OrderStatus orderStatus = orderStatusRepository.findById(1L)
                .orElseThrow(() ->
                        new ResourceNotFoundException("orderStatusId", "orderStatusId", 1));

        List<Cart> cartList = cartRepository.findUserCart(userId)
                .orElseThrow(() -> new ResourceNotFoundException("userId", "userId", userId));

        Order order = new Order();
        order.setAddress(address);
        order.setOrderStatus(orderStatus);
        order.setAppUser(appUser);

        order = orderRepository.save(order);

        for (Cart cart : cartList) {
            OrderedProduct orderedProduct = new OrderedProduct();
            orderedProduct.setOrder(order);
            orderedProduct.setTitle(cart.getProduct().getTitle());
            orderedProduct.setDescription(cart.getProduct().getDescription());
            orderedProduct.setPrice(cart.getProduct().getPrice());
            orderedProduct.setCount(cart.getCount());
            orderedProductRepository.save(orderedProduct);
            cartRepository.delete(cart);
        }

        return getUserOrders(userId);
    }

    public List<OrderResponse> getUserOrders(Long userId) {
        List<Order> orderList = orderRepository.findUserOrder(userId)
                .orElseThrow(() -> new ResourceNotFoundException("userId", "userId", userId));
        return orderDto.mapToOrderResponse(orderList);
    }

    public List<OrderResponse> getAllOrders() {
        List<Order> orderList = orderRepository.findAll();
        return orderDto.mapToOrderResponse(orderList);
    }

    public List<OrderStatus> getAllOrderStatus() {
        return orderStatusRepository.findAll();
    }

    public List<OrderResponse> updateOrderStatus(Long orderId, Long statusId) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new ResourceNotFoundException("orderId", "orderId", orderId));

        OrderStatus orderStatus = orderStatusRepository.findById(statusId)
                .orElseThrow(() -> new ResourceNotFoundException("statusId", "statusId", statusId));

        order.setOrderStatus(orderStatus);

        orderRepository.save(order);

        return getAllOrders();
    }

//    public List<Order> findById(Long id) {
//        List<Order> orderList = orderRepository.findByProductId(id)
//                .orElseThrow(() -> new ResourceNotFoundException("Ordered", "Ordered", id));
//
//        return orderList;
//    }
//
//    public Order updateById(OrderRequest orderRequest) {
//       Order order = orderDto.mapToApplied(orderRequest);
//        Optional<Product> product = orderedProductRepository.findById(orderRequest.getProductId());
//        AppUser appUser = userService.findUserById(orderRequest.getUserId());
//        order.setAppUser(appUser);
//        order.setProduct(product);
//        orderRepository.save(order);
//        return order;
//    }
}


