package com.example.springpractice.converter;
import com.example.springpractice.core.dtos.ProductCompositionDTO;
import com.example.springpractice.core.dtos.RecipeDTO;
import com.example.springpractice.core.dtos.RecipeForCUDTO;
import com.example.springpractice.entity.ProductEntity;
import com.example.springpractice.entity.ProductModalEntity;
import com.example.springpractice.entity.RecipeEntity;
import com.example.springpractice.service.IService.ICategoryService;
import com.example.springpractice.service.IService.IProductService;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class RecipeConverter {
    private final IProductService productService;

    private final ICategoryService categoryService;
    private final ProductConverter productConverter;

    public RecipeConverter(IProductService productService, ICategoryService categoryService, ProductConverter productConverter) {
        this.productService = productService;
        this.categoryService = categoryService;
        this.productConverter = productConverter;
    }

    public RecipeEntity convertToRecipeEntity(RecipeForCUDTO recipeDTO) {
        RecipeEntity entity = new RecipeEntity();
        entity.setTitle(recipeDTO.getTitle());
        entity.setCategory(categoryService.findByTitle(recipeDTO.getCategory().getTitle()));
        List<ProductModalEntity> list = new ArrayList<>();
        for (var c : recipeDTO.getComposition()) {
            ProductEntity productEntity = productService.find(c.getProduct());
            list.add(new ProductModalEntity(productEntity, c.getWeight()));
        }
        entity.setComposition(list);
        entity.setDescription(recipeDTO.getDescription());
        return entity;
    }

    public RecipeDTO convertTORecipeDTO(RecipeEntity entity) {
        RecipeDTO recipeDTO = new RecipeDTO();
        recipeDTO.setUuid(entity.getUuid());
        recipeDTO.setDtCreate(entity.getDtCreate());
        recipeDTO.setDtUpdate(entity.getDtUpdate());
        recipeDTO.setTitle(entity.getTitle());
        recipeDTO.setDescription(entity.getDescription());
        recipeDTO.setCategory(categoryService.find(entity.getCategory().getUuid()));
        List<ProductCompositionDTO> compositionDTOS = entity.getComposition().stream()
                .map(productConverter::convertToProductCompositionDTO).toList();
        recipeDTO.setComposition(compositionDTOS);
        recipeDTO.setWeight(countWeight(compositionDTOS));
        recipeDTO.setCalories(countCalories(compositionDTOS));
        recipeDTO.setProteins(countProteins(compositionDTOS));
        recipeDTO.setFats(countFats(compositionDTOS));
        recipeDTO.setCarbohydrates(countCarbohydrates(compositionDTOS));
        return recipeDTO;
    }

    private Integer countWeight(List<ProductCompositionDTO> list) {
        return list.stream().mapToInt(ProductCompositionDTO::getWeight).sum();
    }

    private Integer countCalories(List<ProductCompositionDTO> list) {
        return list.stream().mapToInt(ProductCompositionDTO::getCalories).sum();
    }

    private Double countProteins(List<ProductCompositionDTO> list) {
        return list.stream().mapToDouble(ProductCompositionDTO::getProteins).sum();
    }

    private Double countFats(List<ProductCompositionDTO> list) {
        return list.stream().mapToDouble(ProductCompositionDTO::getFats).sum();
    }

    private Double countCarbohydrates(List<ProductCompositionDTO> list) {
        return list.stream().mapToDouble(ProductCompositionDTO::getCarbohydrates).sum();
    }

    public ICategoryService getCategoryService() {
        return categoryService;
    }
}