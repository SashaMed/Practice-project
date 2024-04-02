package com.example.springpractice.repository;

import com.example.springpractice.entity.ProductEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;
@Repository
public interface ProductRepository extends CrudRepository<ProductEntity, UUID> {

}