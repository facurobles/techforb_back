package com.techforb_back.configuracion;

import com.techforb_back.jwt.JwtFiltroSolicitudes;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class ConfiguracionSeguridad {
    
    
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http, JwtFiltroSolicitudes jwtFiltroSolicitudes) throws Exception {
        http.csrf().disable()     //desactivo Cross-site
            .authorizeHttpRequests(authz -> authz
                .requestMatchers("/autenticacion/login", "/autenticacion/register").permitAll() //desprotego estas rutas
                .anyRequest().authenticated()       //protejo las demas 
            )
            .addFilterBefore(jwtFiltroSolicitudes, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }
    
    
}
