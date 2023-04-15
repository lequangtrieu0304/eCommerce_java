package com.ecommerce.ecommerce_java.model;

import javax.persistence.*;

@Entity
@Table(name = "cart_item")
public class CartItems {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    @ManyToOne
    @JoinColumn(name = "cart_id")
    private Carts carts;

    private int quantity;

    public CartItems(Product product, Carts carts, int quantity) {
        this.product = product;
        this.carts = carts;
        this.quantity = quantity;
    }

    public CartItems() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Carts getCart() {
        return carts;
    }

    public void setCart(Carts carts) {
        this.carts = carts;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
