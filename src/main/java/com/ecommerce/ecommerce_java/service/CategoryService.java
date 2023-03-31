package com.ecommerce.ecommerce_java.service;

import com.ecommerce.ecommerce_java.model.Category;
import com.ecommerce.ecommerce_java.repository.CatetoryRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {
    @Autowired
    CatetoryRepo catetoryRepo;
    public void createCategory(Category category){
        catetoryRepo.save(category);
    }
    public List<Category> listCategory(){
        return catetoryRepo.findAll();
    }

    public void updateCategory(int id, Category updateCategory) {
        Category category = catetoryRepo.getById(id);
        category.setCategoryName(updateCategory.getCategoryName());
        category.setDescription(updateCategory.getDescription());
        catetoryRepo.save(category);
    }

    public boolean findCategoryById(int id) {
        return catetoryRepo.findById(id).isPresent();
    }
}
