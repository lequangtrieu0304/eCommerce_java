package com.ecommerce.ecommerce_java.model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name="category")
public class Categorys {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name="category_name")
    private @NotBlank String categoryName;
    private @NotBlank String description;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
