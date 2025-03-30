package com.springboot.dev_spring_boot_demo.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

@Entity
@Table(name = "customer")
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "name_company")
    @NotEmpty (message = "Không được để trống")
    @Pattern(regexp = "^(?=.*[a-zA-Z])(?=.*[0-9])[a-zA-Z0-9]+$", message = "bat buoc phai nhap ca so va chu")
    private String nameCompany;

    @Column(name = "street_address")
    @NotEmpty (message = "Không được để trống")
    @Pattern(regexp = "^(?=.*[a-zA-Z])(?=.*[0-9])[a-zA-Z0-9]+$", message = "bat buoc phai nhap ca so va chu")
    private String streetAddress;

    @Column(name = "city")
    @NotEmpty (message = "Không được để trống")
    private String city;

    @Column(name = "region")
    @NotEmpty (message = "Không được để trống")
    private String region;

    @Column(name = "postal")
    @NotEmpty (message = "Không được để trống")
    private String postal;

    @Column(name = "country")
    @NotEmpty(message = "phai chon 1 khu vuc")
    private String country;

    public Customer() {
    }

    public Customer(String nameCompany, String streetAddress, String city, String region, String postal, String country) {
        this.nameCompany = nameCompany;
        this.streetAddress = streetAddress;
        this.city = city;
        this.region = region;
        this.postal = postal;
        this.country = country;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNameCompany() {
        return nameCompany;
    }

    public void setNameCompany(String nameCompany) {
        this.nameCompany = nameCompany;
    }

    public String getStreetAddress() {
        return streetAddress;
    }

    public void setStreetAddress(String streetAddress) {
        this.streetAddress = streetAddress;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getPostal() {
        return postal;
    }

    public void setPostal(String postal) {
        this.postal = postal;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "id=" + id +
                ", nameCompany='" + nameCompany + '\'' +
                ", streetAddress='" + streetAddress + '\'' +
                ", city='" + city + '\'' +
                ", region='" + region + '\'' +
                ", postal='" + postal + '\'' +
                ", country='" + country + '\'' +
                '}';
    }
}