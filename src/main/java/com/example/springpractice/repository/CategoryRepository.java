package com.example.springpractice.repository;

import com.example.springpractice.entity.CategoryEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface CategoryRepository extends CrudRepository<CategoryEntity, UUID> {
    CategoryEntity findByTitle(String title);
}