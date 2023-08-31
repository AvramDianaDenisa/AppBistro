package com.example.bistro.bistroapp.console;

import com.example.bistro.bistroapp.entity.Ingredient;
import com.example.bistro.bistroapp.service.IngredientService;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

@Component
public class ConsolHandlerIngredient {
private  final IngredientService ingredientService;
    private final Scanner scanner = new Scanner(System.in);

    public ConsolHandlerIngredient(IngredientService ingredientService) {
        this.ingredientService = ingredientService;
    }

    public void addIngredient(){
        Set<Ingredient> addedIngredients = enterIngredients();
        for (Ingredient ingredient : addedIngredients) {
            ingredientService.addIngredient(ingredient);
        }
        System.out.println("Ingredients added successfully.");
    }

    public void listAllIngredient(){
        List<Ingredient> allIngredients = ingredientService.getAllIngredients();
        System.out.println("All Ingredients:");
        for (Ingredient ingredient : allIngredients) {
            System.out.println(ingredient.getId() + ": " + ingredient.getName());
        }
    }
    private Set<Ingredient> enterIngredients() {
        Set<Ingredient> ingredients = new HashSet<>();
        boolean addingIngredients = true;
        while (addingIngredients) {
            System.out.println("Enter ingredient name (0 to finish adding ingredients): ");
            String ingredientName = scanner.nextLine();

            if (ingredientName.equals("0")) {
                addingIngredients = false;
            } else {
                System.out.println("Enter ingredient cost: ");
                double ingredientCost = scanner.nextDouble();
                scanner.nextLine();

                Ingredient ingredient = new Ingredient();
                ingredient.setName(ingredientName);
                ingredient.setCost(ingredientCost);

                ingredients.add(ingredient);
            }
        }

        return ingredients;
    }
}
