package com.ecommerce.ecommerce_java.service;

import com.ecommerce.ecommerce_java.dto.ProductDto;
import com.ecommerce.ecommerce_java.exceptions.NotFoundException;
import com.ecommerce.ecommerce_java.model.Category;
import com.ecommerce.ecommerce_java.model.Product;
import com.ecommerce.ecommerce_java.repository.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService{
    @Autowired
    ProductRepo productRepo;

    public void createProduct(ProductDto productDto, Category category) {
        Product product = new Product();
        product.setName(productDto.getName());
        product.setDesciption(productDto.getDesciption());
        product.setCategory(category);
        product.setPrice(productDto.getPrice());
        productRepo.save(product);
    }

    public ProductDto getProductDto(Product product){
        ProductDto productDto = new ProductDto();
        productDto.setName(product.getName());
        productDto.setDesciption(product.getDesciption());
        productDto.setPrice(product.getPrice());
        productDto.setCategoryId(product.getCategory().getId());
        productDto.setId(product.getId());
        return productDto;
    }
    public List<ProductDto> getAllProducts() {
        List<Product> products = productRepo.findAll();
        List<ProductDto> productDtos = new ArrayList<>();
        for(Product product : products){
            productDtos.add(getProductDto(product));
        }
        return productDtos;
    }

    public void updateProduct(ProductDto productDto, Integer id) {
        Optional<Product> optionalProduct = productRepo.findById(id);
        //throw an exception if product does not exist
        if(!optionalProduct.isPresent()){
            throw new NotFoundException("product not present");
        }
        Product product = optionalProduct.get();
        product.setName(productDto.getName());
        product.setDesciption(productDto.getDesciption());
        product.setPrice(productDto.getPrice());
        productRepo.save(product);
    }

    public Product findById(Integer id) throws NotFoundException{
        Optional<Product> optionalProduct = productRepo.findById(id);
        if(optionalProduct.isEmpty()){
            throw new NotFoundException("Not Found Product");
        }
        return optionalProduct.get();
    }
}
