package kg.alatoo.corp.config;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

public class SecurityConfig {
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            .csrf(csrf -> csrf.disable())
            .authorizeHttpRequests(authz -> authz
                .requestMatchers("/ws/**", "/static/**", "/", "/index.html", "/style.css", "/main.js").permitAll()
                .anyRequest().permitAll()
            )
            .formLogin(form -> form.permitAll());
        return http.build();
    }
}