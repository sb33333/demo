package com.demo.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.CacheControl;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    // 개발 시점에 사용 가능한 코드.
    // CacheControl cacheControl = CacheControl.maxAge(Duration.ofMillis(0L)).mustRevalidate();
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry
          .addResourceHandler("/static/**")
          .addResourceLocations("classpath:/static/");
          //.setCacheControl(cacheControl);
    }
}
