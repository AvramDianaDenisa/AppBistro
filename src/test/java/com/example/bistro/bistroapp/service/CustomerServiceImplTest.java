package com.example.bistro.bistroapp.service;

import com.example.bistro.bistroapp.entity.Customer;
import com.example.bistro.bistroapp.repository.CustomerRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.junit.jupiter.api.DisplayName;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;
import org.junit.jupiter.api.Assertions;
import org.springframework.dao.EmptyResultDataAccessException;

public class CustomerServiceImplTest {

    @Mock
    private CustomerRepository customerRepository;

    private CustomerServiceImpl customerService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        customerService = new CustomerServiceImpl(customerRepository);
    }

    private static final Long CUSTOMER_ID = 1L;
    private static final Long NON_EXISTENT_CUSTOMER_ID = 100L;
    private static final String FIRST_NAME = "Avram";
    private static final String LAST_NAME = "Diana";

    @Test
    @DisplayName("Test adding a customer")
    public void testAddCustomer() {
        Customer customer = new Customer();
        customer.setFirstName(FIRST_NAME);
        customer.setLastName(LAST_NAME);

        when(customerRepository.save(any(Customer.class))).thenReturn(customer);

        Customer result = customerService.addCustomer(customer);

        verify(customerRepository, times(1)).save(customer);
        Assertions.assertEquals(customer, result);
        Assertions.assertEquals(FIRST_NAME, result.getFirstName());
        Assertions.assertEquals(LAST_NAME, result.getLastName());
    }

    @Test
    @DisplayName("Test get all customers")
    public void testGetAllCustomers() {
        List<Customer> customers = new ArrayList<>();
        Customer customer1 = new Customer();
        customer1.setFirstName("Popescu");
        customer1.setLastName("Maria");
        customers.add(customer1);
        when(customerRepository.findAll()).thenReturn(customers);

        List<Customer> result = customerService.getAllCustomers();

        verify(customerRepository, times(1)).findAll();
        Assertions.assertEquals(customers, result);
        Assertions.assertEquals("Popescu", result.get(0).getFirstName());
        Assertions.assertEquals("Maria", result.get(0).getLastName());
    }
    @Test
    @DisplayName("Test adding a null customer")
    public void testAddNullCustomer() {
        when(customerRepository.save(isNull())).thenThrow(IllegalArgumentException.class);

        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            customerService.addCustomer(null);
        });

        verify(customerRepository, times(1)).save(isNull());
    }
    @Test
    @DisplayName("Test remove a non-existent customer")
    public void testRemoveNonExistentCustomer() {
        Long nonExistentCustomerId = 100L;
        doThrow(EmptyResultDataAccessException.class).when(customerRepository).deleteById(eq(nonExistentCustomerId));

        Assertions.assertThrows(EmptyResultDataAccessException.class, () -> {
            customerService.removeCustomer(nonExistentCustomerId);
        });

        verify(customerRepository, times(1)).deleteById(eq(nonExistentCustomerId));
    }
    @Test
    @DisplayName("Test get all customers with empty list")
    public void testGetAllCustomersEmptyList() {
        when(customerRepository.findAll()).thenReturn(new ArrayList<>());

        List<Customer> result = customerService.getAllCustomers();

        verify(customerRepository, times(1)).findAll();
        Assertions.assertTrue(result.isEmpty());
    }
    @Test
    @DisplayName("Test remove a customer")
    public void testRemoveCustomer() {
        Long customerId = 1L;

        customerService.removeCustomer(customerId);

        verify(customerRepository, times(1)).deleteById(customerId);
    }
}
