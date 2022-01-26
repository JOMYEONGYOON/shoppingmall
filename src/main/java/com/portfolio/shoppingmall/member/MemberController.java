package com.portfolio.shoppingmall.member;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MemberController {

    @GetMapping("/join")
    public String memberJoin(){
        return "join";
    }

}
