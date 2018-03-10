package com.eshop.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@EnableWebSecurity
@EnableGlobalMethodSecurity(
        prePostEnabled = true,
        securedEnabled = true
)
@Configuration
public class  WebSecurityConfiguration extends WebSecurityConfigurerAdapter{

    @Bean
    public AuthenticationFilter authentificationFilter(){
        return new AuthenticationFilter();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests()
                .antMatchers("/users/create**").permitAll()
                .antMatchers("/users/login**").permitAll()
                .antMatchers("/users/create/**").permitAll()
                .antMatchers("/users/login/**").permitAll()
                .antMatchers("/users/promote**").hasRole("ADMIN")
                .antMatchers("/users/demote**").hasRole("ADMIN")
                .antMatchers("/users/promote/**").hasRole("ADMIN")
                .antMatchers("/users/demote/**").hasRole("ADMIN")
                .antMatchers("/products/create**").hasRole("SELLER")
                .antMatchers("/products/create**").hasRole("ADMIN")
                .antMatchers("/products/delete**").hasRole("SELLER")
                .antMatchers("/products/delete**").hasRole("ADMIN")
                .antMatchers("/products/create/**").hasRole("SELLER")
                .antMatchers("/products/create/**").hasRole("ADMIN")
                .antMatchers("/products/delete/**").hasRole("SELLER")
                .antMatchers("/products/delete/**").hasRole("ADMIN")
                .antMatchers("/products/update**").hasRole("SELLER")
                .antMatchers("/products/update**").hasRole("ADMIN")
                .antMatchers("/products/update/**").hasRole("SELLER")
                .antMatchers("/products/update/**").hasRole("ADMIN")
                .antMatchers("/purchases/getsells**").hasRole("SELLER")
                .antMatchers("/purchases/getsells**").hasRole("ADMIN")
                .antMatchers("/purchases/getsells/**").hasRole("SELLER")
                .antMatchers("/purchases/getsells/**").hasRole("ADMIN")
                .antMatchers("/test/start**").permitAll()
                .anyRequest().authenticated();
        http.addFilterBefore(authentificationFilter(), UsernamePasswordAuthenticationFilter.class);
    }
}