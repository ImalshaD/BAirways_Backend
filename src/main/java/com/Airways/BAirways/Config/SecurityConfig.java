package com.Airways.BAirways.Config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import java.util.concurrent.ExecutionException;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
    @Bean
    public UserDetailsManager userDetailsManager(){
        return new DatabaseUserDetailManager();
    }

    @Bean
    public SecurityFilterChain configure(HttpSecurity http) throws Exception{
////        return http
////                .csrf().disable()
////               .authorizeHttpRequests().requestMatchers(new AntPathRequestMatcher("/registerdUser/**")).hasRole("USER")
////                .and().authorizeHttpRequests().requestMatchers(new AntPathRequestMatcher("/testing/**")).permitAll()
////                .anyRequest().permitAll()
////
////                .build();
        return http.csrf().disable()
                .authorizeHttpRequests().requestMatchers(new AntPathRequestMatcher("/css/**")).permitAll()
                .and().authorizeHttpRequests().requestMatchers(new AntPathRequestMatcher("/js/**")).permitAll()
                .and().authorizeHttpRequests().requestMatchers(new AntPathRequestMatcher("/images/**")).permitAll()
                .and().authorizeHttpRequests().requestMatchers(new AntPathRequestMatcher("/registerdUser/saveUser")).permitAll()
                .and().authorizeHttpRequests().requestMatchers(new AntPathRequestMatcher("/trip/**")).permitAll()
                .anyRequest().authenticated()
                .and()
                .formLogin().loginPage("/login").permitAll()
                .defaultSuccessUrl("/home",true)
                .and().logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout")).clearAuthentication(true).deleteCookies()
                .and().build();
//        return http.authorizeHttpRequests().anyRequest().authenticated().and().build();
    }
}

