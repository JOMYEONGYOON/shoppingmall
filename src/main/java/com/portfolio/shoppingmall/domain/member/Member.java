package com.portfolio.shoppingmall.domain.member;

import com.portfolio.shoppingmall.dto.MemberDto;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@AllArgsConstructor
@Entity
@Getter @Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
//@Builder(builderMethodName = "MemberBuilder")
public class Member {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String email; // 로그인 아이디

    private String password;

    private String name;

    private String phone;

    private String role;

//
//    public static MemberBuilder builder(MemberDto memberDto) {
//        return MemberBuilder()
//                .id(memberDto.getId())
//                .email(memberDto.getEmail())
//                .password(memberDto.getPassword())
//                .name(memberDto.getName())
//                .phone(memberDto.getPhone());
//    }

}
