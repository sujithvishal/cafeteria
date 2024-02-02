package com.softwareag.cafeteria.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.servlet.WebMvcAutoConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CustomWebConfig   implements WebMvcConfigurer  {

//    @Autowired
//    private JwtInterceptor jwtInterceptor;

//    public void addInterceptors(InterceptorRegistry registry){
//        registry.addInterceptor(jwtInterceptor);
//    }

    public void addCorsMapping(CorsRegistry registry){
        registry.addMapping("/**").allowedOrigins("*").allowedMethods("GET","POST","UPDATE","PUT","DELETE","OPTIONS")
                .allowedHeaders("*").exposedHeaders("*");

    }


}
