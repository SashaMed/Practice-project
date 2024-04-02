package com.example.springpractice.core.dtos;

import com.example.springpractice.core.BaseEssence;
import com.example.springpractice.core.dtos.CategoryDTO;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import jakarta.validation.constraints.*;

import java.util.List;

@JsonPropertyOrder(value = {
        "uuid",
        "dtcreate",
        "dtupdate",
        "title",
        "category",
        "composition",
        "weight",
        "calories",
        "proteins",
        "fats",
        "carbohydrates"
})
public class RecipeDTO extends BaseEssence {
    @NotBlank
    private String title;

    @NotNull
    private CategoryDTO category;
    @NotNull
    @Size(min = 1)
    List<ProductCompositionDTO> composition;
    @Positive
    private Integer weight;

    @Positive
    private Integer calories;
    @Positive
    //@JsonSerialize(using = DoubleSerializer.class)
    private Double proteins;
    @Positive
    //@JsonSerialize(using = DoubleSerializer.class)
    private Double fats;
    @Positive
    //JsonSerialize(using = DoubleSerializer.class)
    private Double carbohydrates;

    @NotBlank
    private String description;

    public RecipeDTO(String title, CategoryDTO category, List<ProductCompositionDTO> composition,
                     Integer weight, Integer calories, Double proteins, Double fats, Double carbohydrates, String description) {
        this.title = title;
        this.category = category;
        this.composition = composition;
        this.weight = weight;
        this.calories = calories;
        this.proteins = proteins;
        this.fats = fats;
        this.carbohydrates = carbohydrates;
        this.description = description;
    }

    public RecipeDTO() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<ProductCompositionDTO> getComposition() {
        return composition;
    }

    public void setComposition(List<ProductCompositionDTO> composition) {
        this.composition = composition;
    }

    public Integer getWeight() {
        return weight;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public CategoryDTO getCategory() {
        return category;
    }

    public void setCategory(CategoryDTO category) {
        this.category = category;
    }
}