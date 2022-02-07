package com.portfolio.shoppingmall.domain.cart;

import javax.persistence.Embeddable;

@Embeddable
public class CartLine {

    private Long orderItemId;
    private Integer orderCount;

}
