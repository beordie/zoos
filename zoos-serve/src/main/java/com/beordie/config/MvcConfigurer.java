package com.beordie.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @Description //TODO
 * @Version 1
 * @Date 2023/3/2 11:18
 * @User beordie
 */
@Configuration
public class MvcConfigurer implements WebMvcConfigurer {
    @Autowired
    private CommonConfig config;

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/image/**").addResourceLocations("file:" + config.getDirectory());
    }
}
