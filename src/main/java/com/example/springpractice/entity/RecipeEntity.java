package com.example.springpractice.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "recipe", schema = "public")
public class RecipeEntity {
    @Id
    @GeneratedValue
    @Column(name = "uuid")
    private UUID uuid;
    @Column(name = "dtcreate")
    private Instant dtCreate;

    @Column(name = "dtupdate")
    @Version
    private Instant dtUpdate;
    private String title;
    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(name = "productmodal", schema = "public",
            joinColumns = @JoinColumn(name = "recipeid"),
            inverseJoinColumns = @JoinColumn(name = "productid"))
    @NotNull
    @Size(min = 1)
    private List<ProductModalEntity> composition;

    @ManyToOne
    @JoinColumn(name = "categoryid")
    private CategoryEntity category;

    @Column(name = "description")
    private String description;

    public RecipeEntity() {
    }

    public RecipeEntity(UUID uuid, Instant dtCreate, Instant dtUpdate, String title,
                        List<ProductModalEntity> composition, CategoryEntity category, String description) {
        this.uuid = uuid;
        this.dtCreate = dtCreate;
        this.dtUpdate = dtUpdate;
        this.title = title;
        this.composition = composition;
        this.category = category;
        this.description = description;
    }

    public void addComposition(ProductModalEntity productModel){

    }

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public Instant getDtCreate() {
        return dtCreate;
    }

    public void setDtCreate(Instant dtCreate) {
        this.dtCreate = dtCreate;
    }

    public Instant getDtUpdate() {
        return dtUpdate;
    }

    public void setDtUpdate(Instant dtUpdate) {
        this.dtUpdate = dtUpdate;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<ProductModalEntity> getComposition() {
        return composition;
    }

    public void setComposition(List<ProductModalEntity> composition) {
        this.composition = composition;
    }

    public CategoryEntity getCategory() {
        return category;
    }

    public void setCategory(CategoryEntity category) {
        this.category = category;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}