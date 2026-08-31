package com.ahmed.jobportal.security;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.RegexRequestMatcher;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class JobPortalSecurityConfig {

    @Qualifier("publicPaths")
    private final List<String> publicPaths;

    @Qualifier("securedPaths")
    private final List<String> securedPaths;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) {
        http.authorizeHttpRequests(
                request -> {
                    publicPaths.forEach(path -> request.requestMatchers(path).permitAll());
                    securedPaths.forEach(path -> request.requestMatchers(path).authenticated());
                    request.anyRequest().denyAll();

                }
                // any api end with public
                //.requestMatchers(RegexRequestMatcher.regexMatcher(".*public$")).permitAll()
                //.requestMatchers("/api/swagger-ui.html",
                //        "/swagger-ui/**",
                //        "/api/v3/api-docs/**",
                //        "/swagger-resources/**",
                //        "/swagger-ui.html",
                //        "/webjars/**").permitAll()
                //.requestMatchers("/api/companies").authenticated()
                //.requestMatchers("/api/contacts").permitAll()
                // .anyRequest().authenticated()
                // .anyRequest().permitAll()
                // .anyRequest().denyAll()
        );

        http.cors(corsConfig ->
                corsConfig.configurationSource(corsConfigurationSource()));

        // not recommended for the production
        http.csrf(c -> c.disable());
        // http.csrf(AbstractHttpConfigurer::disable); // the same

        // disable the login from HTML page
        http.formLogin(form -> form.disable());
        http.httpBasic(withDefaults());

        return http.build();
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration config = new CorsConfiguration();

        config.setAllowedOrigins(Arrays.asList("http://localhost:5173"));
        config.setAllowedMethods(Collections.singletonList("*"));
        config.setAllowedHeaders(Collections.singletonList("*"));

        config.setAllowCredentials(true);
        config.setMaxAge(3600L);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", config);

        return source;
    }


}
