package com.kritica.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.sql.Date;

@Entity
@Table(name = "category",
        uniqueConstraints = {
            @UniqueConstraint(columnNames = {"name"})},
        indexes ={
            @Index(name = "idx_category_name", columnList = "name"),
                @Index(name = "idx_category_created_date", columnList = "created_date")}
        )

public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "cat_seq_gen")
    @SequenceGenerator(name = "cat_seq_gen", sequenceName = "CAT_SEQ", initialValue = 1, allocationSize = 1)
    private Long id;

    @NotNull(message = "Category name cannot be null")
    @Size(min=3, max=20, message="Category must contain at least 3 character")
    @Column(name = "name")
    private String name;
    @Size(max=150, message="Description must contain at most 150 character")
    @Column(name = "description")
    private String description;
    @Column(name = "image_url")
    private String imageUrl;
    @Column(name = "image_alt")
    private String imageAlt;
    @Column(name = "create_by")
    @Size(max=20, message="Create by must contain at most 20 character")
    private String create_by;
    @Column(name = "update_by")
    @Size(max=20, message="Update by must contain at most 20 character")
    private String update_by;

    @CreationTimestamp
  //  @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_at", nullable = false, updatable = false)
    private Date createdAt;

    @UpdateTimestamp
    //@Temporal(TemporalType.TIMESTAMP)
    @Column(name = "updated_at", nullable = false)
    private Date updatedAt;


    public Category() {
    }

    public Category(Long id, String name, String description, String imageUrl, String imageAlt, String create_by, String update_by, Date createdAt, Date updatedAt) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.imageUrl = imageUrl;
        this.imageAlt = imageAlt;
        this.create_by = create_by;
        this.update_by = update_by;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getImageAlt() {
        return imageAlt;
    }

    public void setImageAlt(String imageAlt) {
        this.imageAlt = imageAlt;
    }

    public String getCreate_by() {
        return create_by;
    }

    public void setCreate_by(String create_by) {
        this.create_by = create_by;
    }

    public String getUpdate_by() {
        return update_by;
    }

    public void setUpdate_by(String update_by) {
        this.update_by = update_by;
    }
}
