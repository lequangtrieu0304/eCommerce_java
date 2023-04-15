package com.ecommerce.ecommerce_java.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name="products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private @NotNull String name;
    private @NotNull double price;
    private @NotNull String desciption;
    @ManyToOne
    @JsonIgnore
    @JoinColumn(name="category_id")
    Categorys categorys;
    private double rating;
    private int numberReviews;

    public Product(Integer id, String name, double price, String desciption, Categorys categorys) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.desciption = desciption;
        this.categorys = categorys;
    }
    public Product() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getDesciption() {
        return desciption;
    }

    public void setDesciption(String desciption) {
        this.desciption = desciption;
    }

    public Categorys getCategory() {
        return categorys;
    }

    public void setCategory(Categorys categorys) {
        this.categorys = categorys;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public int getNumberReviews() {
        return numberReviews;
    }

    public void setNumberReviews(int numberReviews) {
        this.numberReviews = numberReviews;
    }
}
