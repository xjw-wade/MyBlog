package top.wade.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import top.wade.interceptor.AccessLimitInterceptor;

/**
 * @Author xjw
 * @Date 2023/6/17 15:29
 * @Description: 配置CORS跨域支持、拦截器
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Autowired
    AccessLimitInterceptor accessLimitInterceptor;

    /**
     * 跨域请求
     *
     * @param registry
     */
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("*")
                .allowedHeaders("*")
                .allowedMethods("GET", "POST", "PUT", "DELETE", "HEAD", "OPTIONS")
                .maxAge(3600);
    }

    /**
     * 请求拦截器
     *
     * @param registry
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(accessLimitInterceptor);
    }



}
