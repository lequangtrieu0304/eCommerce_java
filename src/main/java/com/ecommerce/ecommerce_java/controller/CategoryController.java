package com.ecommerce.ecommerce_java.controller;

import com.ecommerce.ecommerce_java.common.ApiResponse;
import com.ecommerce.ecommerce_java.model.Categorys;
import com.ecommerce.ecommerce_java.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/category")
public class CategoryController {
    @Autowired
    CategoryService categoryService;
    @PostMapping("/create")
    public ResponseEntity<ApiResponse> createCategory(@RequestBody Categorys categorys){
        categoryService.createCategory(categorys);
        return new ResponseEntity<>(new ApiResponse(true, "create category success"), HttpStatus.CREATED);
    }
    @GetMapping("/list")
    public ResponseEntity<List<Categorys>> listCategory(){
        List<Categorys> categorys = categoryService.listCategory();
        return new ResponseEntity<>(categorys, HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<ApiResponse> updateCategory(@PathVariable("id") int id, @RequestBody Categorys categorys){
        if(!categoryService.findCategoryById(id)){
            return new ResponseEntity<>(new ApiResponse(false, "category does not exits"), HttpStatus.NOT_FOUND);
        }
        categoryService.updateCategory(id, categorys);
        return new ResponseEntity<>(new ApiResponse(true, "updated"), HttpStatus.OK);
    }
}
