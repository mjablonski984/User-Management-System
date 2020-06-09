package com.urs.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

// Configuration class for authentication and authorization

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private DataSource dataSource;

    // Configure jdbcAuthentication
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        // .usersByUsernameQuery - return users credentials (by email from loginform);
        // authoritiesByUsernameQuery - get the user role (user_roles table -
        // generated with ManyToMany relationship);
        // passwordEncoder - compare password with the password in the db
        // .rolePrefix("ROLE_") - acces role as e.g ROLE_ADMIN, ROLE_USER;
        auth.jdbcAuthentication().dataSource(dataSource)
                .usersByUsernameQuery(
                        "select email as principal, password as credentails, true from user where email=?")
                .authoritiesByUsernameQuery(
                        "select user_email as principal, role_name as role from user_roles where user_email=?")
                .passwordEncoder(passwordEncoder()).rolePrefix("ROLE_");

    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // antMatchers(routes and resources available without login).permitAll()
        // formLogin().loginPage("/login").permitAll()-post request to login route
        // and custom login form
        // antMatchers("/users", "/addTask").hasRole("ADMIN") - accesible only by admin
        // defaultSuccessUrl("/profile") - on succesfull login redirect to profile page
        http.authorizeRequests().antMatchers("/register", "/", "/about", "/login", "/css/**", "/webjars/**").permitAll()
                .antMatchers("/profile").hasAnyRole("USER,ADMIN").antMatchers("/users", "/addTask").hasRole("ADMIN")
                .and().formLogin().loginPage("/login").permitAll().defaultSuccessUrl("/profile").and().logout()
                .logoutSuccessUrl("/login");
    }

}
