package com.bilgeadam.banket.config.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@Configuration
@EnableMethodSecurity
@EnableWebSecurity
public class BanketSecurityConfig {

    // Volkan: NOT FINAL
    private final String[] WHITELIST = {
            "/swagger-ui/**",
            "/v3/api-docs/**",
            "/api/v1/auth/login",
            "/api/v1/user/**",
            "/api/v1/survey/**",
            "/api/v1/student/**",
            "/api/v1/question/**",
            "/api/v1/group/**",
            "/api/v1/filled-survey/**",
            "/api/v1/sended-survey/**",
            "/api/v1/answer/**",
            "/api/v1/auth/**",
            "/api/v1/test/**",
            "/api/v1/mail/**",

    };

    @Bean
    public JwtTokenFilter getJwtTokenFilter() {
        return new JwtTokenFilter();
    }
/*
    Volkan: Password encoder da eklendi fakat şimdilik gerek olmadığı düşünüldüğü için (ayrıca ne kadar güvenilir?) yorum satırına eklendi.
    @Bean
    public PasswordEncoder getPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }
*/

    @Bean
    public UserDetailsService getUserDetailsService() {
        return new JwtUserDetails();
    }

    @Bean
    public AuthenticationManager getAuthenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }

    @Bean
    public AuthenticationProvider getAuthenticationProvider() {
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(getUserDetailsService());
//        authenticationProvider.setPasswordEncoder(getPasswordEncoder());
        return authenticationProvider;
    }

    @Bean
    public SecurityFilterChain getSecurityFilterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity
                .cors(cors -> cors.configurationSource(request -> {
                    CorsConfiguration config = new CorsConfiguration();
                    config.addAllowedOrigin("http://localhost:5173");
                    config.addAllowedHeader("*");
                    config.addAllowedMethod("*");
                    config.setAllowCredentials(true);
                    return config;
                }))
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers("/api/v1/survey/delete-question").authenticated()
                        .anyRequest().permitAll())
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authenticationProvider(getAuthenticationProvider())
                .addFilterBefore(getJwtTokenFilter(), UsernamePasswordAuthenticationFilter.class)
                .build();
    }

//    Volkan: Not recommended uyarısı veriyordu...
//    @Bean
//    public WebSecurityCustomizer webSecurityCustomizer() {
//        return web -> web.debug(false)
//                .ignoring()
//                .requestMatchers("/css/**", "/js/**", "/img/**", "/lib/**", "/favicon.ico");
//    }

    // CORS Yapılandırması
    private CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration corsConfiguration = new CorsConfiguration();
        corsConfiguration.addAllowedOrigin("http://localhost:5173");
        corsConfiguration.addAllowedHeader("*");
        corsConfiguration.addAllowedMethod("*");
        corsConfiguration.setAllowCredentials(true);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", corsConfiguration);

        return source;
    }

}
