package org.project.entity;

import java.sql.Timestamp;
import java.util.UUID;

public class Review {
    private UUID uuid;
    private String text;
    private UUID recipeId;
    private UUID parentReviewId;
    private UUID userId;
    private int likes;
    private Timestamp dtCreate;
    private int rating;

    public Review(UUID uuid, String text, UUID recipeId, UUID parentReviewId, UUID userId, int likes, Timestamp dtCreate, int rating) {
        this.uuid = uuid;
        this.text = text;
        this.recipeId = recipeId;
        this.parentReviewId = parentReviewId;
        this.userId = userId;
        this.likes = likes;
        this.dtCreate = dtCreate;
        this.rating = rating;
    }

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public UUID getRecipeId() {
        return recipeId;
    }

    public void setRecipeId(UUID recipeId) {
        this.recipeId = recipeId;
    }

    public UUID getParentReviewId() {
        return parentReviewId;
    }

    public void setParentReviewId(UUID parentReviewId) {
        this.parentReviewId = parentReviewId;
    }

    public UUID getUserId() {
        return userId;
    }

    public void setUserId(UUID userId) {
        this.userId = userId;
    }

    public int getLikes() {
        return likes;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }

    public Timestamp getDtCreate() {
        return dtCreate;
    }

    public void setDtCreate(Timestamp dtCreate) {
        this.dtCreate = dtCreate;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

}
