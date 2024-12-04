package com.example.Electrical.store.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@Configuration
public class CorsConfig {

    @Bean
    public CorsFilter corsFilter() {
        CorsConfiguration config = new CorsConfiguration();
        config.addAllowedOriginPattern("http://localhost:3000"); // Дозволити фронтенд
        config.addAllowedMethod("*"); // Дозволити всі HTTP-методи (GET, POST тощо)
        config.addAllowedHeader("*"); // Дозволити всі заголовки
        config.setAllowCredentials(true); // Дозволити передачу токенів (наприклад, Cookies)

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", config); // Дозволити для всіх маршрутів
        return new CorsFilter(source);
    }
}
