package com.portfolio.shoppingmall.dto;

import com.portfolio.shoppingmall.domain.item.Items;
import lombok.*;

import java.time.LocalDateTime;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString
@Setter
@Getter
public class ItemsDto {

    private Long id;

    private String name;

    private Long price;

    private String description;

    private String imageUrl;

    private String color;

    private String size;

    private Long discount;

    private Long categoryId;

    private LocalDateTime createTimestamp;

    private LocalDateTime updateTimestamp;

    private Long discountPrice;



    public Items toEntity(){
        Items items = Items.builder()
                .id(id)
        .name(name)
        .price(price)
        .description(description)
        .imageUrl(imageUrl)
        .color(color)
        .size(size)
        .discount(discount)
        .categoryId(categoryId)
                .build();
        return items;

    }

    @Builder
    public ItemsDto(Long id,String name, Long price, String description, String imageUrl, String color, String size, Long discount, Long categoryId) {
        this.id = id;
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
