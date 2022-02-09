package com.portfolio.shoppingmall.service;

import com.portfolio.shoppingmall.domain.cart.Cart;
import com.portfolio.shoppingmall.domain.item.Items;
import org.springframework.stereotype.Service;

import java.util.List;

public interface CartService {

    //create
    void save(Cart cart);

    //read
    List<Items> findCart();

    //quantity update

    //delete items

}
