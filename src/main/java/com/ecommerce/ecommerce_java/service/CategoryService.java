package com.ecommerce.ecommerce_java.service;

import com.ecommerce.ecommerce_java.model.Categorys;
import com.ecommerce.ecommerce_java.repository.CatetoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {
    @Autowired
    CatetoryRepository catetoryRepository;
    public void createCategory(Categorys categorys){
        catetoryRepository.save(categorys);
    }
    public List<Categorys> listCategory(){
        return catetoryRepository.findAll();
    }

    public void updateCategory(int id, Categorys updateCategorys) {
        Categorys categorys = catetoryRepository.getById(id);
        categorys.setCategoryName(updateCategorys.getCategoryName());
        categorys.setDescription(updateCategorys.getDescription());
        catetoryRepository.save(categorys);
    }

    public boolean findCategoryById(int id) {
        return catetoryRepository.findById(id).isPresent();
    }
}
