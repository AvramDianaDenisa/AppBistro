package com.example.bistro.bistroapp.dto;

import com.example.bistro.bistroapp.entity.Customer;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;

public class OrderDTO {
    @Positive(message = "Order total amount must be positive")
    private double totalAmount;

    @NotBlank(message = "Customer name cannot be blank")
    private Customer customer;

    public OrderDTO(double totalAmount, Customer customer) {
        this.totalAmount = totalAmount;
        this.customer = customer;
    }

    public OrderDTO() {

    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    @Override
    public String toString() {
        return "OrderDTO{" +
                "totalAmount=" + totalAmount +
                ", customer=" + customer +
                '}';
    }
}
