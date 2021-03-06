package com.portfolio.shoppingmall.security.config;
import javax.sql.DataSource;

import com.portfolio.shoppingmall.security.provider.CustomAuthenticationProvider;
import com.portfolio.shoppingmall.security.service.CustomUserDetailsService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationDetailsSource;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;

@Configuration
@EnableWebSecurity
@Slf4j
@AllArgsConstructor
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {


    private final DataSource dataSource;
    private final AuthenticationFailureHandler customFailureHandler;
    private final AuthenticationSuccessHandler successHandler;
    private final UserDetailsService userDetailsService;
    private final AuthenticationDetailsSource authenticationDetailsSource;
    @Bean
    public PasswordEncoder passwordEncoder(){
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }



    @Bean
    public AuthenticationProvider authenticationProvider() {
        return new CustomAuthenticationProvider();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/myitems").authenticated()
                .antMatchers("/mypage","/order").hasRole("USER")
                .antMatchers("/messages").hasRole("MANAGER")
                .anyRequest().permitAll()
                .and()

                .formLogin()
                .loginPage("/login")
                .loginProcessingUrl("/login_proc")
//                .permitAll()
                .usernameParameter("email")
                .authenticationDetailsSource(authenticationDetailsSource)
                .defaultSuccessUrl("/")
                .successHandler(successHandler)
                .failureHandler(customFailureHandler)
                .permitAll();
//                .and()
//                .logout().logoutSuccessUrl("/").permitAll();

        //?????? ?????????
        http.sessionManagement()
                .maximumSessions(1) //?????? ?????? ?????? ???
                .maxSessionsPreventsLogin(false); // false?????? ?????? ??????????????? ?????? ???????????? ?????????.

//        http.csrf().csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse());
        http.csrf().disable();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(authenticationProvider());
    }

}