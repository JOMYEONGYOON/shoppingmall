package com.portfolio.shoppingmall.domain;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString
@Getter
@Entity
@Table(uniqueConstraints = @UniqueConstraint(columnNames = "id"))
public class Items {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private Long price;

    private String description;

    private String imageUrl;

    private String color;

    private String size;

    private Long discount;

    private Long categoryId;

    @CreationTimestamp
    private LocalDateTime createTimestamp;

    @UpdateTimestamp
    private LocalDateTime updateTimestamp;

    @Builder
    public Items(String name, Long price, String description, String imageUrl, String color, String size, Long discount, Long categoryId) {
        this.name = name;
        this.price = price;
        this.description = description;
        this.imageUrl = imageUrl;
        this.color = color;
        this.size = size;
        this.discount = discount;
        this.categoryId = categoryId;
    }

}
