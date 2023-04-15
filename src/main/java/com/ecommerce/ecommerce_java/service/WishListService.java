package com.ecommerce.ecommerce_java.service;

import com.ecommerce.ecommerce_java.exceptions.NotFoundException;
import com.ecommerce.ecommerce_java.model.WishList;
import com.ecommerce.ecommerce_java.repository.WishListRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class WishListService {
    @Autowired
    WishListRepository wishListRepository;
    public void createWishList(WishList wishList) {
        wishListRepository.save(wishList);
    }

    public void deleteWishList(Integer id) {
        Optional<WishList> optionalWishList = wishListRepository.findById(id);
        if(optionalWishList.isEmpty()){
            throw new NotFoundException("NOT FOUND WISHLIST");
        }
        wishListRepository.delete(optionalWishList.get());
    }
    public List<Object[]> userHaveWishList(){
        List<Object[]> result = wishListRepository.userHaveWishList();
        return result;
    }
}
