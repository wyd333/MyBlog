package com.example.demo.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Created with IntelliJ IDEA.
 * Description: 系统配置文件
 * User: 12569
 * Date: 2023-08-07
 * Time: 9:52
 */
@Configuration
public class MyConfig implements WebMvcConfigurer {
    /**
     * 设置拦截规则
     * @param registry
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LoginIntercept())
                .addPathPatterns("/**")     //全部拦截
                //放开不需要拦截的接口
                .excludePathPatterns("/user/login")
                .excludePathPatterns("/user/reg")
                .excludePathPatterns("/art/detail")
                .excludePathPatterns("/art/getlistbypage")
                .excludePathPatterns("/css/*")
                .excludePathPatterns("/js/*")
                .excludePathPatterns("/img/*")
                .excludePathPatterns("/editor.md/*")
                .excludePathPatterns("/blog_content.html")
                .excludePathPatterns("/blog_list.html")
                .excludePathPatterns("/login.html")
                .excludePathPatterns("/reg.html");
    }
}
