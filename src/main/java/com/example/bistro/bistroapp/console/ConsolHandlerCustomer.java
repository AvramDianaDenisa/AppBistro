package com.example.bistro.bistroapp.console;

import com.example.bistro.bistroapp.entity.Customer;
import com.example.bistro.bistroapp.entity.Product;
import com.example.bistro.bistroapp.service.CustomerService;
import com.example.bistro.bistroapp.service.IngredientService;
import com.example.bistro.bistroapp.service.ProductService;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import org.springframework.data.domain.Pageable;
import java.util.List;
import java.util.Scanner;

@Component
public class ConsolHandlerCustomer {
    private final ProductService productService;
    private final CustomerService customerService;
    private final IngredientService ingredientService;
    private final Scanner scanner = new Scanner(System.in);

    public ConsolHandlerCustomer(ProductService productService, CustomerService customerService, IngredientService ingredientService) {
        this.productService = productService;
        this.customerService = customerService;
        this.ingredientService = ingredientService;
    }
    public void addCustomer()
    {
        System.out.println("Enter customer first name:");
        String firstName = scanner.next();
        System.out.println("Enter customer last name:");
        String lastName = scanner.next();

        Customer newCustomer = new Customer();
        newCustomer.setFirstName(firstName);
        newCustomer.setLastName(lastName);

        Customer addedCustomer = customerService.addCustomer(newCustomer);
        System.out.println("Added customer with ID: " + addedCustomer.getId());
    }
    public void listAllCustomers()
    {
        List<Customer> allCustomers = customerService.getAllCustomers();
        System.out.println("All Customers:");
        for (Customer customer : allCustomers) {
            System.out.println(customer.getId() + ": " + customer.getFirstName() + " " + customer.getLastName());
        }
    }
    public void deleteCustomer()
    {
        System.out.println("Enter the customer ID:");
        Long customerIdToRemove = scanner.nextLong();

        try {
            customerService.removeCustomer(customerIdToRemove);
            System.out.println("Removed customer with ID " + customerIdToRemove);
        } catch (Exception e) {
            System.out.println("Error removing customer: " + e.getMessage());
        }
    }
}
