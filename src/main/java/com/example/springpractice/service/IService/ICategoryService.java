package com.example.springpractice.service.IService;

import com.example.springpractice.core.dtos.CategoryDTO;
import com.example.springpractice.entity.CategoryEntity;

import java.util.UUID;

public interface ICategoryService {
    UUID add(CategoryDTO category);

    UUID update(UUID uuid, CategoryDTO category);

    CategoryDTO find(UUID uuid);

    CategoryEntity findByTitle(String title);
}
