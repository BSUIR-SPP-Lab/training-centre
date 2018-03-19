package com.bsuir.trainingcenter.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    private static final String queryUsersByUsername = "SELECT `user`.`login` AS `username`, `user`.`password`, " +
            "`user`.`enabled` FROM `user` WHERE `user`.`login` = ?";
    private static final String queryAuthoritiesByUsername = "SELECT `user`.`login` AS `user`.`username`, " +
            "`user`.`role` FROM `user` WHERE `user`.`login` = ?";

    @Autowired
    private DataSource dataSource;

    @Autowired
    protected void configureAuthentication(AuthenticationManagerBuilder auth, PasswordEncoder passwordEncoder) throws Exception {
        auth.jdbcAuthentication().passwordEncoder(passwordEncoder).dataSource(dataSource).rolePrefix("ROLE_")
                .usersByUsernameQuery(queryUsersByUsername)
                .authoritiesByUsernameQuery(queryAuthoritiesByUsername);
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/user/add").permitAll()
                .antMatchers("/login.html", "/swagger-ui.html", "/webjars/**").permitAll()
                .anyRequest().permitAll()
                .and()
                .formLogin()
                .loginPage("/login")
                .permitAll()
                .and()
                .logout()
                .permitAll().and().csrf().disable();
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/v2/api-docs", "/configuration/ui", "/swagger-resources/**", "/configuration/**", "/swagger-ui.html", "/webjars/**");
    }


}
