package com.portfolio.shoppingmall.domain.cart;

import com.portfolio.shoppingmall.domain.item.Items;

import com.portfolio.shoppingmall.domain.member.Member;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;


@Getter @Setter
@Entity

public class Cart {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "item_id" , nullable = false, updatable = false)
    private Items items;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id" , nullable = false, updatable = false)
    private Member member;

    private String clientid;

    private int quantity;


}
