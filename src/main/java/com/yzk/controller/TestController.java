package com.yzk.controller;

import com.yzk.entity.User;
import com.yzk.util.UserThreadLocal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @GetMapping("/test")
    public User test(){
        return UserThreadLocal.getCurrentUser();
    }
}
