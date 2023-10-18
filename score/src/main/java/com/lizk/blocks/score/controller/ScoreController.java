package com.lizk.blocks.score.controller;

import com.lizk.blocks.score.model.Score;
import com.lizk.blocks.score.three.UserRemote;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class ScoreController {


    @Resource
    UserRemote userRemote;


    @GetMapping("/score/get")
    public Score getScore(){
        Map map = userRemote.GetUser();
        System.out.println(map.get("age"));
        return new Score();
    }
}
