package com.portfolio.shoppingmall.controller;

import com.portfolio.shoppingmall.domain.member.Member;
import com.portfolio.shoppingmall.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@Slf4j
@RequiredArgsConstructor
public class OrderController {

    private final MemberService memberService;

    @GetMapping("/order")
    public String order(){
        return "order/orderForm";
    }

    @GetMapping("/user/mypage/charge/point")
    public @ResponseBody void chargePoint(Long amount) {
        System.out.println(amount);
        Member byEmail = memberService.findByEmail("hairhold@naver.com");

    }


}
