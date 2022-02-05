package com.portfolio.shoppingmall.repository;

import com.portfolio.shoppingmall.domain.member.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member,Long> {
    Member findByEmail(String email);
}