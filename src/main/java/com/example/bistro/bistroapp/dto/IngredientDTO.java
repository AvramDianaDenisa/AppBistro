package com.example.bistro.bistroapp.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;

public class IngredientDTO {
    @NotBlank(message = "Ingredient name cannot be blank")
    private String name;

    @Positive(message = "Ingredient cost must be positive")
    private double cost;

    public IngredientDTO(String name, double cost) {
        this.name = name;
        this.cost = cost;
    }

    public IngredientDTO() {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    @Override
    public String toString() {
        return "IngredientDTO{" +
                "name='" + name + '\'' +
                ", cost=" + cost +
                '}';
    }
}
