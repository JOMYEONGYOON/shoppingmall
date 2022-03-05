package com.portfolio.shoppingmall.service.impl;

import com.portfolio.shoppingmall.domain.Member;
import com.portfolio.shoppingmall.dto.MemberDto;
import com.portfolio.shoppingmall.repository.MemberRepository;
import com.portfolio.shoppingmall.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository;

    @Override
    public Member findByEmail(String email) {
        return memberRepository.findByEmail(email);
    }

    @Transactional
    @Override
    public void save(Member member) {
        memberRepository.save(member);
    }


}
