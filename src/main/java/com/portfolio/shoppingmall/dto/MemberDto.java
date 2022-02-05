package com.portfolio.shoppingmall.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class MemberDto {

    private Long id;

    @NotNull
    private String email; // 로그인 아이디
    @NotNull
    private String password;
    @NotNull
    private String name;
    @NotNull
    private String phone;

}
