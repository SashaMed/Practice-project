package com.example.springpractice.service.IService;

import com.example.springpractice.core.dtos.RecipeForCUDTO;

import java.util.UUID;

public interface IRecipeService {
    UUID add(RecipeForCUDTO recipe);

    UUID update(UUID uuid, RecipeForCUDTO recipe);

}