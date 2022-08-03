package com.example.mmproject.global.security.config;

import com.example.mmproject.global.security.filter.JwtAuthenticationFilter;
import com.example.mmproject.global.security.jwt.JwtTokenProvider;
import com.example.mmproject.global.security.oauth.Oauth2UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@RequiredArgsConstructor
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final JwtTokenProvider jwtTokenProvider;
    private final Oauth2UserService oauth2UserService;
    private final Oauth2SuccessHandler successHandler;


    @Override
    public void configure(WebSecurity web){
        web
                .ignoring().antMatchers("/js/**","/h2-console/**","/css/**","resource/**", "jsp/**");
    }

    //암호화에 필요한 PasswordEncoder를 Bean으로 등록합니다.
    @Bean
    public PasswordEncoder passwordEncoder(){
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

    // authenticatonManager를 Bean 등록합니다.
    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception{
        return super.authenticationManagerBean();
    }
// /oauth2/authorization/google
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .httpBasic().disable()
                .csrf().disable()
                .cors().disable()
                .authorizeRequests()
                .antMatchers("/login").permitAll()
                .antMatchers("/signup").permitAll()
                .antMatchers("/auth/**").permitAll()
                .antMatchers("token/**").permitAll()
                .anyRequest().authenticated()
                .and()
                .oauth2Login()
                    .successHandler(successHandler)
                    .userInfoEndpoint().userService(oauth2UserService);

        http.addFilterBefore(new JwtAuthenticationFilter(jwtTokenProvider), UsernamePasswordAuthenticationFilter.class);

    }
}
