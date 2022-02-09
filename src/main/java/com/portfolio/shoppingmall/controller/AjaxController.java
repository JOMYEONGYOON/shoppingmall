package com.portfolio.shoppingmall.controller;

import com.portfolio.shoppingmall.domain.cart.Cart;
import com.portfolio.shoppingmall.domain.item.Items;
import com.portfolio.shoppingmall.domain.member.Member;
import com.portfolio.shoppingmall.dto.CartDto;
import com.portfolio.shoppingmall.service.CartService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;

@RestController
@RequiredArgsConstructor
@Slf4j
public class AjaxController {

    private final CartService cartService;
    private final ModelMapper modelMapper;

    @PostMapping("/api/user")
    public int save(@RequestBody CartDto cartDto, HttpServletRequest request){
        HttpSession httpSession = request.getSession();
        String sessionId = httpSession.getId();
        modelMapper.getConfiguration().setAmbiguityIgnored(true);
//        map.put("clientId",sessionId);
//        CartDto cartDto = new CartDto();
//        cartDto.setClientid(sessionId);
//        cartDto.setItemId((Integer) map.get("itemId"));
////        cartDto.put("item_id",cartDto.get("itemId"));
//        ModelMapper modelMapper = new ModelMapper();
//        Cart cart = modelMapper.map(cartDto, Cart.class);
//
//        System.out.println(sessionId);
        cartDto.setClientid(sessionId);
        Cart cart = modelMapper.map(cartDto, Cart.class);
        Items items = new Items(cartDto.getItem_id());
        cart.setItem_id(items);
        cartService.save(cart);
        System.out.println(cartDto);
        return 1;
    }


}
