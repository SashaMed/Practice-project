package com.example.springpractice.core.dtos;

import com.example.springpractice.core.BaseEssence;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.time.Instant;
import java.util.UUID;
@JsonPropertyOrder({"uuid",
        "dtcreate",
        "dtupdate",
        "title",
        "calories",
        "proteins",
        "fats",
        "carbohydrates"})
public class ProductDTO extends BaseEssence {
    @NotNull
    @NotBlank
    private String title;
    @NotNull
    @Positive
    private Integer calories;
    @NotNull
    @Positive

    private Double proteins;
    @NotNull
    @Positive

    private Double fats;
    @NotNull
    @Positive
    private Double carbohydrates;

    public ProductDTO() {
    }

    public ProductDTO(String title, Integer calories,
                      Double proteins, Double fats, Double carbohydrates) {
        super(UUID.randomUUID(), Instant.now(), Instant.now());
        this.title = title;
        this.calories = calories;
        this.proteins = proteins;
        this.fats = fats;
        this.carbohydrates = carbohydrates;
    }

    public ProductDTO(UUID uuid, Instant dt_create, Instant dt_update,
                      String title, Integer calories,
                      Double proteins, Double fats, Double carbohydrates) {
        super(uuid, dt_create, dt_update);
        this.title = title;
        this.calories = calories;
        this.proteins = proteins;
        this.fats = fats;
        this.carbohydrates = carbohydrates;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }


    public Integer getCalories() {
        return calories;
    }

    public void setCalories(Integer calories) {
        this.calories = calories;
    }

    public Double getProteins() {
        return proteins;
    }

    public void setProteins(Double proteins) {
        this.proteins = proteins;
    }

    public Double getFats() {
        return fats;
    }

    public void setFats(Double fats) {
        this.fats = fats;
    }

    public Double getCarbohydrates() {
        return carbohydrates;
    }

    public void setCarbohydrates(Double carbohydrates) {
        this.carbohydrates = carbohydrates;
    }

}