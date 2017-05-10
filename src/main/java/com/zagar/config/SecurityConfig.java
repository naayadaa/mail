package com.zagar.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;


/**
 * Created by naayadaa on 03.05.17.
 */
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    public void configAuthentication(AuthenticationManagerBuilder auth) throws Exception {

        auth
                .inMemoryAuthentication().withUser("admin").password("admin").roles("ADMIN");

    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/", "/home", "/test", "/h2-console/**", "/api/v1/all/**").permitAll();
        http.authorizeRequests()
                .antMatchers("/v2/api-docs", "/configuration/ui", "/swagger-resources",
                        "/configuration/security", "/swagger-ui.html", "/webjars*").permitAll();
        http.authorizeRequests()
                .antMatchers("/api/v1/auth/**").authenticated();

        http.cors()
                .configurationSource(corsConfigurationSource());

        http.csrf().disable();

        http.headers().frameOptions().disable(); //needs to view H2 web console

        http.formLogin()
                .loginPage("/login")
                .permitAll()
                .and()
                .logout()
                .permitAll();

    }


    public CorsConfigurationSource corsConfigurationSource() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowCredentials(true);
        config.addAllowedOrigin("http://instaleads-reg-it.biz");
        config.addAllowedOrigin("http://terragram-reg-it.biz");
        config.addAllowedOrigin("http://localhost:9000");
        config.addAllowedHeader("Authorization");
        config.addAllowedHeader("content-type");
        config.addAllowedMethod("GET");
        config.addAllowedMethod("POST");
        config.addAllowedMethod("PUT");
        config.addAllowedMethod("DELETE");
        config.addExposedHeader("Authorization");
        source.registerCorsConfiguration("/**", config);
        return source;
    }




}
