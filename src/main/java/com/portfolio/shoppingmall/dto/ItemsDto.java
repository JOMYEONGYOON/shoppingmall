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




}
