package com.lizk.blocks.score.three;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Map;

@FeignClient(name = "users")
public interface UserRemote {
    @GetMapping(path = "/user/get")
    public Map GetUser();
}
