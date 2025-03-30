package com.gdu_springboot.spring_boot_demo.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {
    @Bean
    public InMemoryUserDetailsManager inMemoryUserDetailsManager() {
        UserDetails user1 = User.builder().username("user1").password("{noop}user1").roles("CUSTOMER").build();
        UserDetails user2 = User.builder().username("user2").password("{noop}user2").roles("MANAGER","CUSTOMER").build();
        UserDetails user3=User.builder().username("user3").password("{noop}user3").roles("ADMIN","MANAGER","CUSTOMER").build();
        return new InMemoryUserDetailsManager(user1,user2, user3);
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(configurer->configurer
                .requestMatchers(HttpMethod.GET,"/api/customers").permitAll()
                .requestMatchers(HttpMethod.GET,"/api/customers/**").permitAll()
                .requestMatchers(HttpMethod.POST,"/api/customers").hasRole("ADMIN")
                .requestMatchers(HttpMethod.PUT,"/api/customers").hasRole("ADMIN")
                .requestMatchers(HttpMethod.DELETE,"/api/customers/**").hasRole("ADMIN")

                .requestMatchers(HttpMethod.GET,"/api/candles").permitAll()
                .requestMatchers(HttpMethod.GET,"/api/candles/**").permitAll()
                .requestMatchers(HttpMethod.POST,"/api/candles").hasRole("ADMIN")
                .requestMatchers(HttpMethod.PUT,"/api/candles").hasRole("ADMIN")
                .requestMatchers(HttpMethod.DELETE,"/api/candles/**").hasRole("ADMIN")

                .requestMatchers(HttpMethod.GET,"/api/users").hasRole("MANAGER")
                .requestMatchers(HttpMethod.GET,"/api/users/**").hasRole("MANAGER")
                .requestMatchers(HttpMethod.POST,"/api/users").hasRole("MANAGER")
                .requestMatchers(HttpMethod.PUT,"/api/users").hasRole("MANAGER")
                .requestMatchers(HttpMethod.DELETE,"/api/users/**").hasRole("MANAGER")

                .requestMatchers(HttpMethod.GET,"/api/authority").hasRole("MANAGER")
                .requestMatchers(HttpMethod.GET,"/api/authority/**").hasRole("MANAGER")
                .requestMatchers(HttpMethod.POST,"/api/authority").hasRole("MANAGER")
                .requestMatchers(HttpMethod.PUT,"/api/authority").hasRole("MANAGER")
                .requestMatchers(HttpMethod.DELETE,"/api/authority/**").hasRole("MANAGER")
        );
        http.httpBasic(Customizer.withDefaults());
        http.csrf(csrf->csrf.disable());
        return http.build();
    }
}
