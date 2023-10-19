package com.lizk.blocks.users.common.spring;

import com.lizk.blocks.common.jwt.JWTUtil;
import com.lizk.blocks.users.model.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import jakarta.annotation.Resource;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Objects;

@Component
public class JwtAuthenticationTokenFilter extends OncePerRequestFilter {

    @Resource
    JWTUtil jwtUtil;
/*
    @Autowired
    RedisCache redisCache;

    @Autowired
    JwtUtilService jwtUtilService;*/
    //
    //@Autowired
    //SysUserService sysUserService;

    //@Autowired
    //UserDetailsServiceImpl userDetailsService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        String token = request.getHeader(JWTUtil.JwtHeader);
        if (!Objects.isNull(token) && !ObjectUtils.isEmpty(token)) {

            //1解析token
            Claims claims = jwtUtil.parseJWT(token);

            // 解析对应的权限以及用户信息
            String username = claims.getId();

            User user = new User();
//            user.setUsername("123");
//            user.setPassword("123");

            //2封装Authentication
            UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(user, null, null);
//
//            //5存入SecurityContextHolder
            SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
        }

        //放行，让后面的过滤器执行
        filterChain.doFilter(request, response);
    }

}