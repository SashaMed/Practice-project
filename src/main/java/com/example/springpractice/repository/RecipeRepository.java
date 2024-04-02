package com.example.springpractice.repository;

import com.example.springpractice.entity.RecipeEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface RecipeRepository extends CrudRepository<RecipeEntity, UUID> {
}