package com.portfolio.shoppingmall.controller;

import com.portfolio.shoppingmall.domain.member.Member;
import com.portfolio.shoppingmall.dto.MemberDto;
import com.portfolio.shoppingmall.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Slf4j
@Controller
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @ModelAttribute("member")
    public MemberDto memberDto(){
        return new MemberDto();
    }

    @GetMapping("/login")
    public String login(){
        return "login";
    }

    @GetMapping("/join")
    public String join(Model model){
        return "join";
    }

    @PostMapping("/join")
    public String registerMember(@ModelAttribute("member") @Valid MemberDto memberDto, BindingResult result){
        Member check = memberService.findByEmail(memberDto.getEmail());
//        if(check != null) {
//            result.rejectValue("email", null,"중복된 아이디가 있습니다.");
//        }
//        if(result.hasErrors()) {return "login";}
        memberService.save(memberDto);
        return "redirect:/join";
    }
}
