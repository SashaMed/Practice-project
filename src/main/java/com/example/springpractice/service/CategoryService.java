package com.example.springpractice.service;

import com.example.springpractice.core.dtos.CategoryDTO;
import com.example.springpractice.entity.CategoryEntity;
import com.example.springpractice.repository.CategoryRepository;
import com.example.springpractice.service.IService.ICategoryService;
import org.springframework.expression.ExpressionException;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class CategoryService implements ICategoryService {
    private final CategoryRepository repository;


    public CategoryService(CategoryRepository repository) {
        this.repository = repository;
    }

    @Override
    public UUID add(CategoryDTO category) {
        CategoryEntity categoryEntity = new CategoryEntity();
        categoryEntity.setTitle(category.getTitle());
        categoryEntity.setUuid(UUID.randomUUID());
        repository.save(categoryEntity);
        return categoryEntity.getUuid();
    }

    @Override
    public UUID update(UUID uuid, CategoryDTO category) {
        CategoryEntity categoryEntity = repository.findById(uuid).orElseThrow(() ->
                new ExpressionException("error", "there is no category with this id : " + uuid));
//        if (productEntity.getDtUpdate().toEpochMilli() != dtUpdate.toEpochMilli()) {
//            throw new SingleErrorResponse("error", "product already has been update");
//        }
        categoryEntity.setTitle(category.getTitle());
        repository.save(categoryEntity);
        return uuid;
    }

    @Override
    public CategoryDTO find(UUID uuid) {
        return new CategoryDTO(repository.findById(uuid).orElseThrow(() ->
                new ExpressionException("error", "there is no category with this id : " + uuid)).getTitle());
    }

    @Override
    public CategoryEntity findByTitle(String title) {
        CategoryEntity categoryEntity = repository.findByTitle(title);
        if(categoryEntity == null)
            throw new ExpressionException("error", "there is no category with this name : " + title);
        return categoryEntity;
    }
}