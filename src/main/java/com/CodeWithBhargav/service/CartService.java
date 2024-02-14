package com.CodeWithBhargav.service;


import com.CodeWithBhargav.exception.common.ResourceNotFoundException;
import com.CodeWithBhargav.model.AppUser;
import com.CodeWithBhargav.model.Cart;
import com.CodeWithBhargav.model.Product;
import com.CodeWithBhargav.repository.CartRepository;
import com.CodeWithBhargav.repository.ProductRepository;
import com.CodeWithBhargav.repository.UserRepository;
import com.CodeWithBhargav.request.CartRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class CartService {

    @Autowired
    private CartRepository cartRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ProductRepository productRepository;

    public Object findUserCart(Long userId) {
        List<Cart> cart = (List<Cart>) cartRepository.findUserCart(userId)
                .orElseThrow(() -> new ResourceNotFoundException("cart", "userId", userId));
        return cart;
    }

    @Transactional
    public Object addToCart(CartRequest cartRequest) {
        AppUser appUser = userRepository.findById(cartRequest.getUserId())
                .orElseThrow(() -> new ResourceNotFoundException("userId", "userId",
                        cartRequest.getUserId()));

        Product product = productRepository.findById(cartRequest.getProductId())
                .orElseThrow(() -> new ResourceNotFoundException("productId", "productId",
                        cartRequest.getProductId()));

        Optional<List<Cart>> cartOptional = cartRepository.findUserCart(cartRequest.getUserId());

        if (cartOptional.isPresent()) {
            boolean isPresent = false;
            for (Cart cart : cartOptional.get()) {
                if (cart.getProduct().getId().equals(cartRequest.getProductId())) {
                    cart.setCount(cartRequest.getCount());
                    cartRepository.save(cart);
                    isPresent = true;
                }
            }
            if (!isPresent) {
                Cart cart = new Cart();
                cart.setAppUser(appUser);
                cart.setProduct(product);
                cart.setCount(cartRequest.getCount());
                cartRepository.save(cart);
            }
        } else {
            Cart cart = new Cart();
            cart.setAppUser(appUser);
            cart.setProduct(product);
            cart.setCount(cartRequest.getCount());
            cartRepository.save(cart);
        }
        return findUserCart(cartRequest.getUserId());

    }

    public Object deleteFromCart(Long userId, Long productCart) {
        return findUserCart(userId);
    }

//    public List<Cart> findAlLUserCart() {
//        return cartRepository.findAllUserCart();
//    }

//    public List<CartResponse> updateCart(CartRequest cartRequest) {
//        public List<CartResponse> updateCart(CartRequest cartRequest) {
//            AppUser appUser = userRepository.findById(cartRequest.getUserId())
//                    .orElseThrow(() -> new ResourceNotFoundException("userId", "userId", cartRequest.getUserId()));//userid
//
//            ArtWork artWork = artWorkRepository.findById(cartRequest.getArtWorkId())
//                    .orElseThrow(() -> new ResourceNotFoundException("ProductId", "ProductId", cartRequest.getArtWorkId()));//productid
//
//            Optional<List<Cart>> cartOptional = cartRepository.findUserCart(cartRequest.getUserId());//for query
//
//            //checking cart present
//            if (cartOptional.isPresent()) {
//                boolean ispresent = false;
//                for (Cart cart : cartOptional.get()) {
//                    if ((cart.getArtWork().getId()) == cartRequest.getArtWorkId()) {
//                        cart.setCount(cartRequest.getCount());//set count
//                        cart.setTotal(cartRequest.getTotal());
//                        cartRepository.save(cart);
//                        ispresent = true;
//
//                    }
//                }
//                //cart not present
//                if (!ispresent) {
//                    Cart cart = new Cart();
//                    cart.setAppUser(appUser);
//                    cart.setArtWork(artWork);
//                    cart.setCount(cartRequest.getCount());
//                    cart.setTotal(cartRequest.getTotal());
//                    cartRepository.save(cart);
//                }
//            } else {
//                Cart cart = new Cart();
//                cart.setAppUser(appUser);
//                cart.setArtWork(artWork);
//                cart.setCount(cartRequest.getCount());
//                cart.setTotal(cartRequest.getTotal());
//                cartRepository.save(cart);
//            }
//            return findUserCart(cartRequest.getUserId());
//        }
//    }
}
