package com.springboot.dev_spring_boot_demo.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import javax.sql.DataSource;

@Configuration
public class SecurityConfig {
    @Bean
    public UserDetailsManager userDetailsManager(DataSource dataSource) {
        JdbcUserDetailsManager jdbcUserDetailsManager = new JdbcUserDetailsManager(dataSource);
        
        // Define the SQL to get users
        jdbcUserDetailsManager.setUsersByUsernameQuery(
                "select username, password, enabled from users where username=?");
        
        // Define the SQL to get authorities
        jdbcUserDetailsManager.setAuthoritiesByUsernameQuery(
                "select username, authority from authorities where username=?");
        
        return jdbcUserDetailsManager;
    }
//    public InMemoryUserDetailsManager inMemoryUserDetailsManager() {
//        UserDetails truong = User.builder()
//                .username("truong")
//                .password("{noop}truong")
//                .roles("STUDENT")
//                .build();
//        UserDetails tru=User.builder()
//                .username("tru")
//                .password("{noop}tru")
//                .roles("STUDENT","MANAGER")
//                .build();
//        UserDetails bao=User.builder()
//                .username("bao")
//                .password("{noop}bao")
//                .roles("MANAGER","STUDENT","ADMIN")
//                .build();
//        return new InMemoryUserDetailsManager(bao);
//    }
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(configurer->
                configurer
                        // Cho phép truy cập static resources
                        .requestMatchers("/manager/css/**", "/manager/js/**", "/manager/vendor/**", "/manager/img/**").permitAll()
                        .requestMatchers("/a/css/**", "/manager/js/**", "/manager/vendor/**", "/manager/img/**").permitAll()
                        // Đường dẫn cho ADMIN
                        .requestMatchers("/admin/**").hasRole("ADMIN")
                        .requestMatchers("/customer").hasRole("ADMIN")
                        .requestMatchers("/candles").hasRole("ADMIN")
                        // Đường dẫn cho MANAGER
                        .requestMatchers("/manager/**").hasRole("MANAGER")
                        .requestMatchers("/user").hasRole("MANAGER")
                        .anyRequest().permitAll()
        ).formLogin(form->
                form.loginPage("/login")
                        .loginProcessingUrl("/authenticateTheUser")
                        .defaultSuccessUrl("/", true)
                        .permitAll()
        )
                .logout(logout ->
                        logout.logoutUrl("/logout")
                                .logoutSuccessUrl("/login?logout")
                                .invalidateHttpSession(true)
                                .deleteCookies("JSESSIONID")
                                .permitAll()
                )
                .csrf(csrf -> csrf.disable())
                .exceptionHandling(configurer ->
                        configurer.accessDeniedPage("/access-denied")
                );
        return http.build();
    }
    @Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }
}
