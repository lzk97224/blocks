package com.lizk.blocks.users.common.spring;


import com.lizk.blocks.common.jwt.JWTUtil;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class NewBean {
    @Value("${jwt.secretKey}")
    @Getter
    @Setter
    public String secretKey;

    @Bean
    public JWTUtil jwtUtil(){
        return new JWTUtil(secretKey);
    }
}
