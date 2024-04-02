package com.example.springpractice.core.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.List;

public class RecipeForCUDTO {
    @NotBlank
    private String title;
    @NotNull
    @Size(min = 1)
    List<ProductModalDTO> composition;

    private CategoryDTO category;

    @NotNull
    private String description;

    public RecipeForCUDTO() {
    }

    public RecipeForCUDTO(String title, List<ProductModalDTO> composition, CategoryDTO category, @NotNull String description) {
        this.title = title;
        this.composition = composition;
        this.category = category;
        this.description = description;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<ProductModalDTO> getComposition() {
        return composition;
    }

    public void setComposition(List<ProductModalDTO> composition) {
        this.composition = composition;
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