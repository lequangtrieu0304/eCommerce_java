package com.ecommerce.ecommerce_java.service;

import com.ecommerce.ecommerce_java.exceptions.NotFoundException;
import com.ecommerce.ecommerce_java.model.WishList;
import com.ecommerce.ecommerce_java.repository.WishListRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class WishListService {
    @Autowired
    WishListRepo wishListRepo;
    public void createWishList(WishList wishList) {
        wishListRepo.save(wishList);
    }

    public void deleteWishList(Integer id) {
        Optional<WishList> optionalWishList = wishListRepo.findById(id);
        if(optionalWishList.isEmpty()){
            throw new NotFoundException("NOT FOUND WISHLIST");
        }
        wishListRepo.delete(optionalWishList.get());
    }
    public List<Object[]> userHaveWishList(){
        List<Object[]> result = wishListRepo.userHaveWishList();
        return result;
    }
}
