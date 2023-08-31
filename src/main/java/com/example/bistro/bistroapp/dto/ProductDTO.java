package com.example.bistro.bistroapp.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

public class ProductDTO {
    @NotBlank(message = "Product name cannot be blank")
    private String name;

    @Min(value = 0, message = "Product price must be a positive number")
    private double price;

    public ProductDTO(String name, double price) {
        this.name = name;
        this.price = price;
    }

    public ProductDTO() {

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

    @Override
    public String toString() {
        return "ProductDTO{" +
                "name='" + name + '\'' +
                ", price=" + price +
                '}';
    }
}
