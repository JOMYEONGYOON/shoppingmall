package com.portfolio.shoppingmall.service;

import com.portfolio.shoppingmall.domain.member.Member;
import com.portfolio.shoppingmall.dto.MemberDto;

public interface MemberService {

    Member findByEmail(String email);

    Member save(MemberDto memberDto);

}
