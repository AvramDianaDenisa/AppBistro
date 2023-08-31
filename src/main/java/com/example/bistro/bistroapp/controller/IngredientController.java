package com.example.bistro.bistroapp.controller;

import com.example.bistro.bistroapp.dto.IngredientDTO;
import com.example.bistro.bistroapp.entity.Ingredient;
import com.example.bistro.bistroapp.service.IngredientService;
import com.example.bistro.bistroapp.entity.IngredientMapper;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(IngredientController.BASE_URL)
public class IngredientController {
    public static final String BASE_URL = "/api/v1/ingredients";
    private final IngredientService ingredientService;
    private final IngredientMapper ingredientMapper;

    public IngredientController(IngredientService ingredientService, IngredientMapper ingredientMapper) {
        this.ingredientService = ingredientService;
        this.ingredientMapper = ingredientMapper;
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public void addIngredient(@Valid @RequestBody IngredientDTO ingredientDTO) {
        Ingredient newIngredient = ingredientMapper.ingredientDTOtoIngredient(ingredientDTO);
        ingredientService.addIngredient(newIngredient);
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public List<IngredientDTO> getAllIngredients() {
        List<Ingredient> ingredients = ingredientService.getAllIngredients();
        return ingredients.stream()
                .map(ingredientMapper::ingredientToIngredientDTO)
                .collect(Collectors.toList());
    }

    @GetMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public IngredientDTO getIngredientById(@PathVariable Long id) {
        Ingredient ingredient = ingredientService.getIngredientById(id);
        return ingredientMapper.ingredientToIngredientDTO(ingredient);
    }
}
