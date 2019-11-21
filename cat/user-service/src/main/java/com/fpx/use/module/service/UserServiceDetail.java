package com.fpx.use.module.service;

import com.fpx.use.module.dao.UserDao;
import com.fpx.use.module.entity.User;
import com.fpx.use.module.service.jwt.AuthServiceClient;
import com.fpx.use.module.service.jwt.JWT;
import com.fpx.use.module.service.jwt.UserLoginDTO;
import com.fpx.use.module.service.jwt.exception.UserLoginException;
import com.fpx.use.utils.BPwdEncoderUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceDetail {

    @Autowired
    private UserDao userRepository;

    @Autowired
    private AuthServiceClient client;

    public User insertUser(String username, String  password){
        User user=new User();
        user.setUsername(username);
        user.setPassword(BPwdEncoderUtil.BCryptPassword(password));
        return userRepository.save(user);
    }

    public UserLoginDTO login(String username, String password){
        User user=userRepository.findByUsername(username);
        if (null == user) {
            throw new UserLoginException("用户名错误！");
        }
        if(!BPwdEncoderUtil.matches(password,user.getPassword())){
            throw new UserLoginException("密码错误！");
        }
        // 获取token
        JWT jwt=client.getToken("Basic dXNlci1zZXJ2aWNlOjEyMzQ1Ng==","password",username,password);
        // 获得用户菜单
        if(jwt==null){
            throw new UserLoginException("网络异常！");
        }
        UserLoginDTO userLoginDTO=new UserLoginDTO();
        userLoginDTO.setJwt(jwt);
        userLoginDTO.setUser(user);
        return userLoginDTO;

    }

}
