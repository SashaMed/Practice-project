package com.example.springpractice.core.dtos;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@JsonPropertyOrder({"uuid", "title"})
public class CategoryDTO{
    @NotNull
    @NotBlank
    private String title;

    public CategoryDTO(@NotNull String title) {
        this.title = title;
    }

    public CategoryDTO(){

    }

    public @NotNull String getTitle() {
        return title;
    }

    public void setTitle(@NotNull String title) {
        this.title = title;
    }
}