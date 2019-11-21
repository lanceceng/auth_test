package com.fpx.use.module.service.jwt;

import com.fpx.use.module.entity.User;
import lombok.Data;

@Data
public class UserLoginDTO {
    private JWT jwt;
    private User user;
}
