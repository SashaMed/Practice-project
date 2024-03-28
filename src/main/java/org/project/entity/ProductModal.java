package org.project.entity;

import java.util.UUID;

public class ProductModal {
    private UUID uuid;
    private UUID recipeId;
    private UUID productId;
    private int weight;

    public ProductModal(UUID uuid, UUID recipeId, UUID productId, int weight) {
        this.uuid = uuid;
        this.recipeId = recipeId;
        this.productId = productId;
        this.weight = weight;
    }

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public UUID getRecipeId() {
        return recipeId;
    }

    public void setRecipeId(UUID recipeId) {
        this.recipeId = recipeId;
    }

    public UUID getProductId() {
        return productId;
    }

    public void setProductId(UUID productId) {
        this.productId = productId;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }
}
