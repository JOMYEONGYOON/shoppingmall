package com.portfolio.shoppingmall.dto;

import com.portfolio.shoppingmall.domain.item.Items;
import lombok.Data;

import javax.persistence.*;

@Data
public class CartDto {

    private Long cartId;

    private Long item_id;

    private String memberId;

    private String clientid;

    private int quantity;

    private int count;



}
