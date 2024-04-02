package com.example.springpractice.service;

import com.example.springpractice.converter.RecipeConverter;
import com.example.springpractice.core.dtos.RecipeForCUDTO;
import com.example.springpractice.entity.RecipeEntity;
import com.example.springpractice.repository.RecipeRepository;
import com.example.springpractice.service.IService.IRecipeService;
import org.springframework.expression.ExpressionException;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.UUID;

@Service
public class RecipeService implements IRecipeService {

    private final RecipeRepository repository;
    private final RecipeConverter converter;


    public RecipeService(RecipeRepository repository, RecipeConverter converter) {
        this.repository = repository;
        this.converter = converter;
    }

    @Override
    public UUID add(RecipeForCUDTO recipe) {
        RecipeEntity entity = converter.convertToRecipeEntity(recipe);
        entity.setUuid(UUID.randomUUID());
        entity.setDtCreate(Instant.now());
        entity.setDtUpdate(Instant.now());
        repository.save(entity);
        System.out.println(entity.getCategory());
        return entity.getUuid();
    }


    @Override
    public UUID update(UUID uuid, RecipeForCUDTO recipe) {
        RecipeEntity entity = repository.findById(uuid).orElseThrow(() ->
                new ExpressionException("error",
                        "recipe with this id: " + uuid + " not found"));
//        if (entity.getDtUpdate().toEpochMilli() != dtUpdate.toEpochMilli()) {
//            throw new SingleErrorResponse("err",
//                    "recipe already has been update");
//        }
        var newEntity = converter.convertToRecipeEntity(recipe);
        entity.setComposition(newEntity.getComposition());
        entity.setTitle(newEntity.getTitle());
        repository.save(entity);
        return uuid;
    }

}