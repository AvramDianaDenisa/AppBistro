package com.example.bistro.bistroapp.entity;

import com.example.bistro.bistroapp.dto.IngredientDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface IngredientMapper {
    IngredientMapper INSTANCE = Mappers.getMapper(IngredientMapper.class);

    IngredientDTO ingredientToIngredientDTO(Ingredient ingredient);

    @Mapping(target = "id", ignore = true)
    Ingredient ingredientDTOtoIngredient(IngredientDTO ingredientDTO);
}
