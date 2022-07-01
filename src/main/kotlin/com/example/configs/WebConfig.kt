package com.example.configs

import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpMethod
import org.springframework.web.servlet.config.annotation.CorsRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer

@Configuration
class WebConfig : WebMvcConfigurer {
    override fun addCorsMappings(registry: CorsRegistry) {
        registry.addMapping("/**")
            .allowedOrigins(
                "http://localhost",
                "http://localhost:3000",
            )
            .allowedHeaders("*")
            .allowedMethods(
                HttpMethod.GET.name,
                HttpMethod.POST.name,
                HttpMethod.OPTIONS.name,
                HttpMethod.PUT.name,
                HttpMethod.DELETE.name
            )
            .allowCredentials(true)
            .maxAge(3600)
    }
}
