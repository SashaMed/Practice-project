package com.example.springpractice.core.dtos;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import org.antlr.v4.runtime.misc.NotNull;

@JsonPropertyOrder({"uuid", "title"})
public class CategoryDTO{
    @NotNull
    //@NotBlank
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
