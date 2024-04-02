package com.example.springpractice.core.dtos;


import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.util.UUID;

public class ProductModalDTO {
    @NotNull
    private UUID product;
    @NotNull
    @Positive
    private Integer weight;

    public ProductModalDTO() {
    }

    public ProductModalDTO(UUID product, Integer weight) {
        this.product = product;
        this.weight = weight;
    }

    public UUID getProduct() {
        return product;
    }

    public Integer getWeight() {
        return weight;
    }
}