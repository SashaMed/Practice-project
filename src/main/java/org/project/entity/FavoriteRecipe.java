package org.project.entity;

import java.util.UUID;

public class FavoriteRecipe {
    private UUID uuid;
    private UUID userId;
    private UUID recipeId;

    public FavoriteRecipe(UUID uuid, UUID userId, UUID recipeId) {
        this.uuid = uuid;
        this.userId = userId;
        this.recipeId = recipeId;
    }

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public UUID getUserId() {
        return userId;
    }

    public void setUserId(UUID userId) {
        this.userId = userId;
    }

    public UUID getRecipeId() {
        return recipeId;
    }

    public void setRecipeId(UUID recipeId) {
        this.recipeId = recipeId;
    }
}
