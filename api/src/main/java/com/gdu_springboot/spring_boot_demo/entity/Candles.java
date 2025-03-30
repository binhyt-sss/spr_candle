package com.gdu_springboot.spring_boot_demo.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "candles")
public class Candles {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "name_candle")
    private String nameCandle;

    @Column(name = "description")
    private String description;

    @Column(name = "content")
    private String content;

    @Column(name = "quantity")
    private int quantity;

    @Column(name = "Price")
    private String price;

    @Column(name = "imageuri")
    private String imageURL;

    public Candles() {
    }

    public Candles(String nameCandle, String description, String content, int quantity, String price, String imageURL) {
        this.nameCandle = nameCandle;
        this.description = description;
        this.content = content;
        this.quantity = quantity;
        this.price = price;
        this.imageURL = imageURL;
    }

    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNameCandle() {
        return nameCandle;
    }

    public void setNameCandle(String nameCandle) {
        this.nameCandle = nameCandle;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    @Override
    public String toString() {
        return "Candles{" +
                "id=" + id +
                ", nameCandle='" + nameCandle + '\'' +
                ", description='" + description + '\'' +
                ", content='" + content + '\'' +
                ", quantity=" + quantity +
                ", price='" + price + '\'' +
                ", imageURL='" + imageURL + '\'' +
                '}';
    }
}