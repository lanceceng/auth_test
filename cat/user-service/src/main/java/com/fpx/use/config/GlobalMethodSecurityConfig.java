package com.fpx.use.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;

/**
 * 新建一个配置类 GlobalMethodSecurityConfig，在此类中通过 @EnableGlobalMethodSecurity(prePostEnabled = true)注解开启方法级别的安全验证。
 */
@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class GlobalMethodSecurityConfig {

}
