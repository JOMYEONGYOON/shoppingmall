package com.portfolio.shoppingmall.security.token;
import com.portfolio.shoppingmall.dto.MemberDto;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

public class AjaxAuthenticationToken extends UsernamePasswordAuthenticationToken {

    private AjaxAuthenticationToken(Object principal, Object credentials, String authorities) {
        super(principal, credentials);
    }

    public static AjaxAuthenticationToken getTokenFromAccountContext(MemberDto memberDto) {
        return new AjaxAuthenticationToken(memberDto, memberDto.getPassword(), memberDto.getRole());
    }
}