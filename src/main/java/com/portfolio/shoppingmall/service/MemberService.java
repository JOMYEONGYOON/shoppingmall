package com.portfolio.shoppingmall.service;

import com.portfolio.shoppingmall.domain.member.Member;
import com.portfolio.shoppingmall.dto.MemberDto;
import org.springframework.stereotype.Service;

import java.util.Optional;

public interface MemberService {

    Member findByEmail(String email);

    void save(Member member);

}
