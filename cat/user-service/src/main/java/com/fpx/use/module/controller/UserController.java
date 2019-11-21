package com.fpx.use.module.controller;

import com.fpx.use.module.entity.User;
import com.fpx.use.module.service.UserServiceDetail;
import com.fpx.use.module.service.jwt.UserLoginDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

//import java.util.UUID;

@RestController
@RequestMapping("/user")
public class UserController {
//    private static final java.util.UUID UUID = ;

    @Autowired
    UserServiceDetail userServiceDetail;

    @PostMapping("/register")
    public User postUser(@RequestParam("username") String username , @RequestParam("password") String password){
        //参数判断，省略
        return userServiceDetail.insertUser(username,password);
    }

    /**
     * 类补充一个登录的API接口“/user/login”.
     * @param username
     * @param password
     * @return
     */
    @PostMapping("/login")
    public UserLoginDTO login(@RequestParam("username") String username , @RequestParam("password") String password){
        //参数判断，省略
        return userServiceDetail.login(username,password);
    }

    /**
     * 为了测试权限，再补充一个"/foo"接口，该接口需要“ROLE_ADMIN”权限.
     * @return
     */
    @RequestMapping(value = "/foo", method = RequestMethod.GET)
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public String getFoo() {
//        return "i'm foo, " + UUID.randomUUID().toString();
        return "i'm foo, " + System.currentTimeMillis();
    }
}
