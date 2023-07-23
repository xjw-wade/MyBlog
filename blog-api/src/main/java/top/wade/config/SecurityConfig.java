package top.wade.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import top.wade.service.impl.UserServiceImpl;

/**
 * @Author xjw
 * @Date 2023/7/10 22:19
 * @Description: Spring Security配置类
 */
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    UserServiceImpl userService;
    @Autowired
    MyAuthenticationEntryPoint myAuthenticationEntryPoint;



    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                //禁用csrf防御
                .csrf().disable()
                //开启跨域支持
                .cors().and();

    }

}