package com.example.springpractice.entity;
import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Table(name = "categories", schema = "public")
public class CategoryEntity {
    @Id
    @GeneratedValue
    @Column(name = "uuid")
    private UUID uuid;

    @Column(name = "title")
    private String title;

    public CategoryEntity() {
    }

    public CategoryEntity(UUID uuid, String title) {
        this.uuid = uuid;
        this.title = title;
    }

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}


