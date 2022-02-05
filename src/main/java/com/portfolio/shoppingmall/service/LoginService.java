package com.portfolio.shoppingmall.service;

import com.portfolio.shoppingmall.domain.member.Member;
import com.portfolio.shoppingmall.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class LoginService {

    private final MemberRepository memberRepository;

//    public Member login(String loginId, String password) {
//        Optional<Member> byEmail = Optional.ofNullable(memberRepository.findByEmail(loginId));
//    }


}
