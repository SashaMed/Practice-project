package com.example.springpractice.service;


import com.example.springpractice.converter.ProductConverter;
import com.example.springpractice.core.dtos.ProductDTO;
import com.example.springpractice.entity.ProductEntity;
import com.example.springpractice.repository.ProductRepository;
import com.example.springpractice.service.IService.IProductService;
import org.springframework.expression.ExpressionException;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.UUID;

@Service
public class ProductService implements IProductService {
    private final ProductRepository repository;
    private final ProductConverter converter;

    public ProductService(ProductRepository repository, ProductConverter converter) {
        this.repository = repository;
        this.converter = converter;
    }

    @Override
    public UUID add(ProductDTO product) {
        ProductEntity productEntity = converter.convertToEntity(product);
        productEntity.setUuid(UUID.randomUUID());
        productEntity.setDtCreate(Instant.now());
        productEntity.setDtUpdate(Instant.now());
        repository.save(productEntity);
        return productEntity.getUuid();
    }

    @Override
    public UUID update(UUID uuid, ProductDTO product) {
        ProductEntity productEntity = repository.findById(uuid).orElseThrow(() ->
                new ExpressionException("error", "there is no product with this id : " + uuid));
//        if (productEntity.getDtUpdate().toEpochMilli() != dtUpdate.toEpochMilli()) {
//            throw new SingleErrorResponse("error", "product already has been update");
//        }
        productEntity.setTitle(product.getTitle());
        productEntity.setCalories(product.getCalories());
        productEntity.setProteins(product.getProteins());
        productEntity.setFats(product.getFats());
        productEntity.setCarbohydrates(product.getCarbohydrates());
        repository.save(productEntity);
        return uuid;
    }


    @Override
    public ProductEntity find(UUID uuid) {
        return repository.findById(uuid).orElseThrow(() ->
                new ExpressionException("error", "there is no product with this id : " + uuid));
    }
}