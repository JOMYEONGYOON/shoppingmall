package com.portfolio.shoppingmall.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.portfolio.shoppingmall.domain.cart.Cart;
import com.portfolio.shoppingmall.domain.item.Items;
import com.portfolio.shoppingmall.domain.member.Member;
import com.portfolio.shoppingmall.dto.CartDto;
import com.portfolio.shoppingmall.security.SessionConst;
import com.portfolio.shoppingmall.service.CartService;
import com.portfolio.shoppingmall.service.ItemsService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Slf4j
@Controller
@RequiredArgsConstructor
public class CartController {

    private final ItemsService itemsService;
    private final CartService cartService;

    @GetMapping("/cart")
    public String cart(Model model, HttpServletRequest request){
        List<Items> cart = cartService.findCart();
        model.addAttribute("cartlist",cart);
        return "cart/cart";
    }






    


}
