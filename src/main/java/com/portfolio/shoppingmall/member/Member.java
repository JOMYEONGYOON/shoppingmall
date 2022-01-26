package com.portfolio.shoppingmall.member;

import lombok.Getter;

@Getter
public class Member {
    private Long id;
    private String email;
    private String password;
    private String username;
    private String phonenumber;
    private String zipcode;

    public Member() {
    }
}
