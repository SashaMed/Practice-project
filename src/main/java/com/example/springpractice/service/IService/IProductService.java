package com.example.springpractice.service.IService;

import com.example.springpractice.core.dtos.ProductDTO;
import com.example.springpractice.entity.ProductEntity;

import java.util.UUID;

public interface IProductService {
    UUID add(ProductDTO product);

    UUID update(UUID uuid, ProductDTO product);

    ProductEntity find(UUID uuid);

}