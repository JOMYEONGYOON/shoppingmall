package com.portfolio.shoppingmall.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
public class MemberDto {

    private Long id;

    @NotBlank(message = "이메일을 입력해주세요.")
    private String email; // 로그인 아이디
    @NotBlank(message = "비밀번호를 입력해주세요.")
    private String password;
    @NotBlank(message = "이름을 입력해주세요.")
    private String name;
    @NotBlank(message = "핸드폰 번호를 입력해주세요.")
    private String phone;
    private String role;

}
