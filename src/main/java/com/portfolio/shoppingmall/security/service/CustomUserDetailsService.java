package com.portfolio.shoppingmall.security.service;

import com.portfolio.shoppingmall.security.CustomUserDetails;
import com.portfolio.shoppingmall.domain.member.Member;
import com.portfolio.shoppingmall.repository.MemberRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private  MemberRepository memberRepository;

    boolean enabled = true;
    boolean accountNonExpired = true;
    boolean credentialsNonExpired = true;
    boolean accountNonLocked = true;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Member member = memberRepository.findByEmail(username);
        if(member == null) {
            throw new UsernameNotFoundException("UsernameNotFoundException");
        }
        List<GrantedAuthority> roles = new ArrayList<>();
        roles.add(new SimpleGrantedAuthority("ROLE_USER"));
//        AccountContext accountContext = new AccountContext(member,roles);

        return new CustomUserDetails(member);
    }
    // 시큐리티의 내용 외 파라미터를 추가하고 싶을 때, 아래 사용
    //  제약조건: Controller 에서 Auth를 점검할 때, UserCustom 으로 받아야 함.
    //  예) (변경 전) @AuthenticationPrincipal User user => (변경 후) @AuthenticationPrincipal UserCustom user


//    @Override
//    public UserDetails loadUserByUsername(String user_email) throws UsernameNotFoundException {
//        // 로그인 시도하려는 유저정보 조회
//        MemberDTO memberDTO = memberMapper.chkLogin(user_email);
//
//        // 조회가 되지않는 고객은 에러발생.
//        if(memberDTO == null){
//            throw new UsernameNotFoundException(user_email);
//        }
//
//        // 조회한 정보를 userCustom에 담는다.
//        // 만약 파라미터를 추가해야한다면 UserCustom 을 먼저 수정한다.
//        UserCustom userCustom = new UserCustom(memberDTO.getUsername()
//                , memberDTO.getPassword()
//                , enabled, accountNonExpired, credentialsNonExpired, accountNonLocked
//                , authorities(memberDTO)
//                , memberDTO.getUser_email()
//                , memberDTO.getUser_name() // 이름
//        );
//
//        return userCustom;
//    }

//    // DB에 등록된 권한에 따라 유저권한 부여 user_role
//    private static Collection authorities(MemberDTO memberDTO){
//        Collection authorities = new ArrayList<>();
//        // DB에 저장한 USER_ROLE 이 1이면 ADMIN 권한, 아니면 ROLE_USER 로 준다.
//        if(memberDTO.getUser_role().equals("1")){
//            authorities.add(new SimpleGrantedAuthority("ADMIN"));
//        }else{
//            authorities.add(new SimpleGrantedAuthority("ROLE_USER"));
//        }
//        return authorities;
//    }
}
