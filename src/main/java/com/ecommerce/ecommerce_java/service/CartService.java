package com.ecommerce.ecommerce_java.service;

import com.ecommerce.ecommerce_java.dto.cart.AddToCartDto;
import com.ecommerce.ecommerce_java.exceptions.NotFoundException;
import com.ecommerce.ecommerce_java.model.Carts;
import com.ecommerce.ecommerce_java.model.CartItems;
import com.ecommerce.ecommerce_java.model.Product;
import com.ecommerce.ecommerce_java.model.User;
import com.ecommerce.ecommerce_java.repository.CartItemRepository;
import com.ecommerce.ecommerce_java.repository.CartRepository;
import com.ecommerce.ecommerce_java.repository.ProductRepository;
import com.ecommerce.ecommerce_java.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;;

@Service
public class CartService {
    @Autowired
    CartRepository cartRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    ProductRepository productRepository;
    @Autowired
    CartItemRepository cartItemRepository;
    public void addToCart(AddToCartDto addToCartDto, User user) {
        User userCart = userRepository.findById(user.getId())
                .orElseThrow(() -> new NotFoundException("NOT FOUND USER"));
        Product findProduct = productRepository.findById(addToCartDto.getProduct_id())
                .orElseThrow(() -> new NotFoundException("NOT FOUND PRODUCT"));
        Carts carts = cartRepository.findByUser(userCart);
        if(carts == null){
            carts = new Carts();
            carts.setUser(userCart);
            carts.setTotalPrice(findProduct.getPrice());
            cartRepository.save(carts);
            CartItems cartItems = new CartItems(findProduct, carts, 1);
            cartItemRepository.save(cartItems);
        }
        else {
            CartItems cartItems = cartItemRepository
                    .findByCart(carts)
                    .stream()
                    .filter(c -> c.getProduct().getId().equals(findProduct.getId()))
                    .findFirst()
                    .orElse(null);
            if(Objects.isNull(cartItems)){
                cartItems = new CartItems();
                cartItems.setProduct(findProduct);
                cartItems.setQuantity(1);
                cartItems.setCart(carts);
            }
            else {
                cartItems.setQuantity(cartItems.getQuantity() + 1);
            }
            cartItemRepository.save(cartItems);
            double totalPrice = cartItemRepository
                    .findByCart(carts)
                    .stream()
                    .reduce(0.0, (subtotal, e) ->
                            subtotal + e.getProduct().getPrice() * (double)e.getQuantity(),
                            Double::sum);
            carts.setTotalPrice(totalPrice);
            cartRepository.save(carts);
        }
    }
}
