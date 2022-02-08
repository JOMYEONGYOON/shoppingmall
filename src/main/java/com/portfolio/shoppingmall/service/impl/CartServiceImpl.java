package com.portfolio.shoppingmall.service.impl;

import com.portfolio.shoppingmall.domain.cart.Cart;
import com.portfolio.shoppingmall.dto.CartDto;
import com.portfolio.shoppingmall.dto.ItemsDto;
import com.portfolio.shoppingmall.repository.CartItemRepository;
import com.portfolio.shoppingmall.service.CartService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import java.util.List;

@RequiredArgsConstructor
@Service
public class CartServiceImpl implements CartService {

    private final CartItemRepository cartItemRepository;


    @Override
    public void save(Cart cart) {
        cartItemRepository.save(cart);
    }

    @Override
    public List<Cart> cartlist(Cart cart) {
        return null;
    }
}
