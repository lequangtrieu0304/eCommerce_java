package com.ecommerce.ecommerce_java.controller;

import com.ecommerce.ecommerce_java.common.ApiResponse;
import com.ecommerce.ecommerce_java.dto.ProductDto;
import com.ecommerce.ecommerce_java.model.Categorys;
import com.ecommerce.ecommerce_java.model.Product;
import com.ecommerce.ecommerce_java.repository.CatetoryRepository;
import com.ecommerce.ecommerce_java.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/product")
public class ProductController {
    @Autowired
    ProductService productService;
    @Autowired
    CatetoryRepository catetoryRepository;
    @PostMapping("/create")
    public ResponseEntity<ApiResponse> createProduct(@RequestBody ProductDto productDto){
        Optional<Categorys> optionalCategory = catetoryRepository.findById(productDto.getCategoryId());
        if(!optionalCategory.isPresent()){
            return new ResponseEntity<>(new ApiResponse(false, "category does not exist"), HttpStatus.BAD_REQUEST);
        }
        productService.createProduct(productDto, optionalCategory.get());
        return new ResponseEntity<>(new ApiResponse(true, "product has been created"), HttpStatus.CREATED);
    }

    @GetMapping("/")
    public ResponseEntity<List<ProductDto>> getAllProducts(){
        List<ProductDto> productList = productService.getAllProducts();
        return new ResponseEntity<>(productList, HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable("id") Integer id){
        Product product = productService.findById(id);
        return new ResponseEntity<>(product, HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<ApiResponse> updateProduct(@PathVariable("id") Integer id, @RequestBody ProductDto productDto) throws Exception {
        Optional<Categorys> optionalCategory = catetoryRepository.findById(productDto.getCategoryId());
        if(!optionalCategory.isPresent()){
            return new ResponseEntity<>(new ApiResponse(false, "category does not exist"), HttpStatus.BAD_REQUEST);
        }
        productService.updateProduct(productDto, id);
        return new ResponseEntity<>(new ApiResponse(true, "product has been updated"), HttpStatus.OK);
    }
}
