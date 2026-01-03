package com.unibank.api.Securities;

import com.unibank.api.Securities.JWTUtils.JwtAuthenticationFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {


    private final UniBankUserDetailsServices uniBankUserDetailsServices;
    private final Encoder encoder;
    private final JwtAuthenticationFilter jwtAuthenticationFilter;

    public SecurityConfig(
            UniBankUserDetailsServices uniBankUserDetailsServices,
            Encoder encoder,
            JwtAuthenticationFilter jwtAuthenticationFilter){

        this.uniBankUserDetailsServices = uniBankUserDetailsServices;
        this.jwtAuthenticationFilter = jwtAuthenticationFilter;
        this.encoder = encoder;
    }


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http){
        http
                .csrf(AbstractHttpConfigurer::disable)
                .formLogin(AbstractHttpConfigurer::disable)
                .httpBasic(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests( auth -> auth
                        .requestMatchers("/auth/register").permitAll()
                        .requestMatchers("/auth/login").permitAll()
                        .requestMatchers("/customers/**").hasAuthority("admin")
                        .requestMatchers("/accounts/**").hasAuthority("admin")
                        .requestMatchers("/transactions/deposit").hasAuthority("admin")
                        .anyRequest().authenticated()
                )
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }

    @Bean
    public AuthenticationManager authenticationManager(){
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider(uniBankUserDetailsServices);
        provider.setPasswordEncoder(encoder.passwordEncoder());
        return new ProviderManager(provider);
    }
}
