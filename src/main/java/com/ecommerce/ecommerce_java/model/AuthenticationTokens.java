package com.ecommerce.ecommerce_java.model;

import javax.persistence.*;
import java.util.Date;
import java.util.UUID;

@Entity
@Table(name="tokens")
public class AuthenticationTokens {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Integer id;
    private String token;
    @Column(name="created_date")
    private Date createdDate;
    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(nullable = false, name = "user_id")
    private User user;
    public AuthenticationTokens(User user){
        this.user = user;
        this.createdDate = new Date();
        this.token = UUID.randomUUID().toString();
    }
    public AuthenticationTokens() {
    }
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
