package com.portfolio.shoppingmall.dto;

import com.portfolio.shoppingmall.domain.item.Items;
import com.portfolio.shoppingmall.domain.member.Member;
import lombok.Data;

@Data
public class CartDto {


    private Long Id;

    private Items items;

    private Member member;

    private String clientid;

    private int quantity;


}
