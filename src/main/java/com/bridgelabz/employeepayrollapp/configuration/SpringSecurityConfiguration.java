//package com.bridgelabz.employeepayrollapp.configuration;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import org.springframework.security.crypto.password.NoOpPasswordEncoder;
//
//@SuppressWarnings("deprecation")
//@Configuration
//public class SpringSecurityConfiguration extends WebSecurityConfigurerAdapter {
//    //mentioning the name and password of specific user role(like: ADMIN)
//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.inMemoryAuthentication()
//                .withUser("Sampriti")
//                .password("password")
//                .roles("ADMIN");
//    }
//
//    //accessing any authorized http request passed to the authentication manager
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        // to disable an attack that forces an end user to execute unwanted actions
//        // on a web application in which they are currently authenticated.
//        http.csrf().disable();
//        //accessing any authorized request
//        http.authorizeRequests().anyRequest().fullyAuthenticated().and().httpBasic();
//    }
//
//    //to depricate the password
//    @Bean
//    public static NoOpPasswordEncoder passwordEncoder() {
//        return (NoOpPasswordEncoder) NoOpPasswordEncoder.getInstance();
//    }
//}
