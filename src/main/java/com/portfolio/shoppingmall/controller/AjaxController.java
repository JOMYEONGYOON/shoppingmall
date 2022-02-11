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
import java.util.List;

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
        cartDto.setClientid(sessionId);
        Cart cart = modelMapper.map(cartDto, Cart.class);
        Items items = new Items(cartDto.getItem_id());
//        cart.setItem_id(items);
//        cartService.save(cart);
//        Long itemId = Long.parseLong(cartDto.getItem_id().toString());
//        Long listItemId;
//        List<Items> cartList = cartService.findCart();
//        List<Cart> byAll = cartService.findByAll();

//        for (int i=0;i< cartList.size();i++) {
//            listItemId = cartList.get(i).getId();
//            List<Cart> itemsList = cartList.get(i).getItemsList();
//            for (Cart cart1 : itemsList) {
//                System.out.println("cart1 = " + cart1);
//            }
//            if(itemId.equals(listItemId)) {
////                boolean byItemId = cartService.findByItemId(listItemId);
//
//                cart.setQuantity(+1);
////
//                break;
//            }else{
////                cartService.save(cart); break;
//            }
//
//        }


        return 1;
    }


}
