package com.example.bistro.bistroapp.service;


import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.example.bistro.bistroapp.entity.Ingredient;
import com.example.bistro.bistroapp.repository.IngredientRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.data.crossstore.ChangeSetPersister;

public class IngredientServiceImplTest {

    @Mock
    private IngredientRepository ingredientRepository;

    @InjectMocks
    private IngredientServiceImpl ingredientService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        ingredientService = new IngredientServiceImpl(ingredientRepository);
    }

    @Test
    @DisplayName("Test adding ingredients")
    public void testAddIngredient() {
        Ingredient ingredient = new Ingredient();
        ingredient.setName("Salt");

        Mockito.when(ingredientRepository.save(Mockito.any(Ingredient.class))).thenReturn(ingredient);

        Ingredient savedIngredient = ingredientService.addIngredient(ingredient);

        assertNotNull(savedIngredient);
        assertEquals("Salt", savedIngredient.getName());

    }

    @Test
    @DisplayName("Test getting all ingredients")
    public void testGetAllIngredients() {
        Ingredient ingredient1 = new Ingredient();
        ingredient1.setName("Salt");

        Ingredient ingredient2 = new Ingredient();
        ingredient2.setName("Pepper");

        List<Ingredient> ingredientList = new ArrayList<>();
        ingredientList.add(ingredient1);
        ingredientList.add(ingredient2);

        when(ingredientRepository.findAll()).thenReturn(ingredientList);

        List<Ingredient> retrievedIngredients = ingredientService.getAllIngredients();

        assertNotNull(retrievedIngredients);
        assertEquals(2, retrievedIngredients.size());
    }

    @Test
    @DisplayName("Test getting ingredient by ID")
    public void testGetIngredientById() {
        Ingredient ingredient = new Ingredient();
        ingredient.setId(1L);
        ingredient.setName("Salt");

        when(ingredientRepository.findById(1L)).thenReturn(java.util.Optional.of(ingredient));

        Ingredient retrievedIngredient = ingredientService.getIngredientById(1L);

        assertNotNull(retrievedIngredient);
        assertEquals("Salt", retrievedIngredient.getName());
    }
    @Test
    @DisplayName("Test adding a null ingredient")
    public void testAddNullIngredient() {
        Mockito.when(ingredientRepository.save(Mockito.isNull())).thenThrow(IllegalArgumentException.class);

        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            ingredientService.addIngredient(null);
        });
    }

}

