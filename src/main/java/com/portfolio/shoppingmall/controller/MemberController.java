package com.portfolio.shoppingmall.controller;

import com.portfolio.shoppingmall.domain.member.Member;
import com.portfolio.shoppingmall.dto.MemberDto;
import com.portfolio.shoppingmall.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
    public String login(@RequestParam(value = "error", required = false) String error,
                        @RequestParam(value = "exception", required = false) String exception,
                        Model model){
        model.addAttribute("error",error);
        model.addAttribute("exception",exception);
        return "login";
    }

    @GetMapping("/join")
    public String join(Model model){
        return "join";
    }

    @PostMapping("/join")
    public String registerMember(@ModelAttribute("member") @Valid MemberDto memberDto, BindingResult result){
        if(result.hasErrors()) {
            return "join";
        }
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encodedPassword = passwordEncoder.encode(memberDto.getPassword());
        memberDto.setPassword(encodedPassword);
//        BCrypt
        memberService.save(memberDto);
        return "redirect:/login";
    }
}
