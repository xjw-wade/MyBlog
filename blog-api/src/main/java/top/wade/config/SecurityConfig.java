package top.wade.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import top.wade.service.LoginLogService;
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
    @Autowired
    LoginLogService loginLogService;

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userService);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                //禁用csrf防御
                .csrf().disable()
                //开启跨域支持
                .cors().and()
                //基于Token,不创建会话
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
                .authorizeRequests()
                //放行获取网页标题后缀的请求
                .antMatchers("/admin/webTitleSuffix").permitAll()
                //任何 /admin 开头的路径下的请求都需要经过JWT验证
                .antMatchers(HttpMethod.GET, "/admin/**").hasAnyRole("admin", "visitor")
                .antMatchers("/admin/**").hasRole("admin")
                //其它路径全部放行
                .anyRequest().permitAll()
                .and()
                //自定义JWT过滤器   addFilterBefore在指定过滤器之前添加自定义的过滤器到过滤器链中
                .addFilterBefore(new JwtLoginFilter("/admin/login", authenticationManager(), loginLogService), UsernamePasswordAuthenticationFilter.class)
                .addFilterBefore(new JwtFilter(), UsernamePasswordAuthenticationFilter.class)
                //未登录时，返回json，在前端执行重定向
                .exceptionHandling().authenticationEntryPoint(myAuthenticationEntryPoint);
    }

}