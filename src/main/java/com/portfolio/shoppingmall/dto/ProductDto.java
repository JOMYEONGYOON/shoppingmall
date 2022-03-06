package com.portfolio.shoppingmall.dto;

import lombok.Builder;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

@Data
public class ProductDto {


    private int id;

    private String name;

    private String slug;

    private String description;

    private String image;

    private String price;

    private String categoryId;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;



//    @Builder
//    public ProductDto(int id, String name, String slug, String description, String image, String price, String categoryId, LocalDateTime createdAt, LocalDateTime updatedAt) {
//        this.id = id;
//        this.name = name;
//        this.slug = slug;
//        this.description = description;
//        this.image = image;
//        this.price = price;
//        this.categoryId = categoryId;
//        this.createdAt = createdAt;
//        this.updatedAt = updatedAt;
//    }

}
