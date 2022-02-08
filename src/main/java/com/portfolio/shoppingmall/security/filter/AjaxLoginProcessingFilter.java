package com.portfolio.shoppingmall.security.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.portfolio.shoppingmall.dto.MemberDto;
import com.portfolio.shoppingmall.security.token.AjaxAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.util.StringUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AjaxLoginProcessingFilter extends AbstractAuthenticationProcessingFilter {

    private ObjectMapper objectMapper = new ObjectMapper();

    protected AjaxLoginProcessingFilter() {
        super(new AntPathRequestMatcher("/api/login"));
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException, IOException, ServletException {

        if (!isAjax(request)) {
            throw new IllegalStateException("Authentication is not supported");
        }

        MemberDto memberDto = objectMapper.readValue(request.getReader(), MemberDto.class);
        if (StringUtils.isEmpty(memberDto.getEmail()) || StringUtils.isEmpty(memberDto.getPassword())) {
            throw new IllegalArgumentException("Username or Password is empty");
        }

//        AjaxAuthenticationToken authenticationToken = new AjaxAuthenticationToken();


        return null;
    }

    private boolean isAjax(HttpServletRequest request) {

        if ("XMLHttpRequest".equals(request.getHeader("X-Requested-With"))) {
            return true;
        }
        return false;
    }

}
