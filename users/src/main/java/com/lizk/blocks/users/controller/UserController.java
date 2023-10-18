package com.lizk.blocks.users.controller;

import com.lizk.blocks.common.jwt.JWTUtil;
import com.lizk.blocks.users.model.LoginUser;
import com.lizk.blocks.users.model.User;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

@RestController
public class UserController {


    @Resource
    private JWTUtil jwtUtil;

    @GetMapping(path = "/api/user/get")
    public User GetUser() {
        var u = new User();
        return u;
    }

    @Autowired
    AuthenticationManager authenticationManager;

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginUser loginUser) {
        // 3使用ProviderManager auth方法进行验证
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(loginUser.getUsername(), loginUser.getPassword());
        // 该方法会去调用UserDetailsServiceImpl.loadUserByUsername
        Authentication authenticate = authenticationManager.authenticate(usernamePasswordAuthenticationToken);
        // 校验失败了
        if (Objects.isNull(authenticate)) {
            return ResponseEntity.notFound().build();
        }
        User userDetails = (User) (authenticate.getPrincipal());

        // 4自己生成jwt token给前端
        String token = jwtUtil.createJWT(userDetails.getUsername(),userDetails.getPassword(),1000*100,null);
        return ResponseEntity.ok(token);
    }
}
