package com.example.finalproject_clinic.config;

import com.example.finalproject_clinic.persistence.entity.security.RolesUser;
import com.example.finalproject_clinic.service.impl.security.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;


@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserService userService;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .authorizeRequests()
                .antMatchers("/api/appointments/**").hasAuthority(RolesUser.USER.name())
                .antMatchers("/api/patients/**", "/api/dentist/**", "/api/appointments/**").hasAuthority(RolesUser.ADMIN.name())
                .antMatchers("/html/index.html", "/html/patients.html", "/html/dentist.html", "/html/appointments.html").hasAuthority(RolesUser.ADMIN.name())
                .antMatchers("/html/appointments.html").hasAuthority(RolesUser.USER.name())
                .anyRequest().authenticated().and()
                .exceptionHandling().accessDeniedPage("/403").and()
                .formLogin().permitAll().and().httpBasic().and()
                .logout(logout -> logout
                        .logoutUrl("/logout.html")
                );
        http.headers().frameOptions().disable();
    }

    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(daoAuthenticationProvider());
    }

    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider() {
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder);
        daoAuthenticationProvider.setUserDetailsService(userService);
        return daoAuthenticationProvider;

    }


}
