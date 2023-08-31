package com.example.bistro.bistroapp.entity;

import com.example.bistro.bistroapp.dto.IngredientDTO;
import org.springframework.stereotype.Component;

@Component
public class IngredientMapperImpl implements IngredientMapper {
    @Override
    public IngredientDTO ingredientToIngredientDTO(Ingredient ingredient) {
        if (ingredient == null) {
            return null;
        }

        IngredientDTO ingredientDTO = new IngredientDTO();

        ingredientDTO.setName(ingredient.getName());
        ingredientDTO.setCost(ingredient.getCost());

        return ingredientDTO;
    }

    @Override
    public Ingredient ingredientDTOtoIngredient(IngredientDTO ingredientDTO) {
        if (ingredientDTO == null) {
            return null;
        }

        Ingredient ingredient = new Ingredient();

        ingredient.setName(ingredientDTO.getName());
        ingredient.setCost(ingredientDTO.getCost());

        return ingredient;
    }
}

