package com.portfolio.shoppingmall.controller;

import com.portfolio.shoppingmall.domain.member.Member;
import com.portfolio.shoppingmall.security.SessionConst;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttribute;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Slf4j
@Controller
public class CartController {

    @GetMapping("/cart")
    public String cart(Model model, HttpServletRequest request){
        HttpSession session = request.getSession();
        String sessionId = session.getId();
//        model.addAttribute("basketId",session.getAttribute());
        log.info("sessionId={}",sessionId);
        return "cart/cart";
    }

    @ResponseBody
    @PostMapping("/cart/addcart")
    public String addcart(){
        System.out.println("dd");
        return "sucess";
    }

    


}
