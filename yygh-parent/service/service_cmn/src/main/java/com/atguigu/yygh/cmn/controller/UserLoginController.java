package com.atguigu.yygh.cmn.controller;

import com.atguigu.yygh.common.result.Result;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/admin/vod/user")
@CrossOrigin
public class UserLoginController {

    /**
     * login
     * @return
     */
    @PostMapping("/login")
    public Result login(){
        Map<String, Object> map = new HashMap<>();
        map.put("token","admin-token");
        return Result.ok(map).code(20000);
    }

    /**
     * info
     * @return
     */
    @GetMapping("/info")
    public Result info(){
        Map<String, Object> map = new HashMap<>();
        map.put("roles","admin");
        map.put("introduction","I am a supper administrator");
        map.put("avatar","https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif");
        map.put("name","Super Admin");
        return Result.ok(map).code(20000);
    }

    @PostMapping("/logout")
    public Result logout(){
        return Result.ok(null);
    }

}
