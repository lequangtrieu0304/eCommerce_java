package com.ecommerce.ecommerce_java.service;

import com.ecommerce.ecommerce_java.dto.cart.AddToCartDto;
import com.ecommerce.ecommerce_java.exceptions.NotFoundException;
import com.ecommerce.ecommerce_java.model.Cart;
import com.ecommerce.ecommerce_java.model.CartItems;
import com.ecommerce.ecommerce_java.model.Product;
import com.ecommerce.ecommerce_java.model.User;
import com.ecommerce.ecommerce_java.repository.CartItem;
import com.ecommerce.ecommerce_java.repository.CartRepo;
import com.ecommerce.ecommerce_java.repository.ProductRepo;
import com.ecommerce.ecommerce_java.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;;

@Service
public class CartService {
    @Autowired
    CartRepo cartRepo;
    @Autowired
    UserRepo userRepo;
    @Autowired
    ProductRepo productRepo;
    @Autowired
    CartItem cartItemRepo;
    public void addToCart(AddToCartDto addToCartDto, User user) {
        User userCart = userRepo.findById(user.getId()).orElseThrow(() -> new NotFoundException("NOT FOUND USER"));
        Product findProduct = productRepo.findById(addToCartDto.getProduct_id()).orElseThrow(() -> new NotFoundException("NOT FOUND PRODUCT"));
        Cart cart = userCart.getCart();
        if(cart == null){
            cart = new Cart();
            cart.setUser(userCart);
            cart.setTotalPrice(findProduct.getPrice());
            cartRepo.save(cart);
            CartItems cartItems = new CartItems(findProduct, cart, 1);
            cartItemRepo.save(cartItems);
        }
        else {
            CartItems cartItems = cart
                    .getCartItems()
                    .stream()
                    .filter(c -> c.getProduct().getId().equals(findProduct.getId()))
                    .findFirst()
                    .orElse(null);
            if(Objects.isNull(cartItems)){
                cartItems = new CartItems();
                cartItems.setProduct(findProduct);
                cartItems.setQuantity(1);
                cartItems.setCart(cart);
            }
            else {
                cartItems.setQuantity(cartItems.getQuantity() + 1);
            }
            cartItemRepo.save(cartItems);
            double totalPrice = cart
                    .getCartItems()
                    .stream()
                    .reduce(0.0, (subtotal, e) -> subtotal + e.getProduct().getPrice() * (double)e.getQuantity(), Double::sum);
            cart.setTotalPrice(totalPrice);
            cartRepo.save(cart);
        }
    }
}
