package com.koolmain.imdb.security;

import com.koolmain.imdb.constants.ImdbSecurityConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.SecurityFilterChain;


@EnableWebSecurity
@Configuration
public class SecurityConfiguration{

    private static final String DEGREE_URL_PATTERN = "/degree/**";
    private static final String NAME_URL_PATTERN = "/name/**";
    private static final String TITLE_URL_PATTERN = "/title/**";
    @Autowired
    @Qualifier("customerAuthenticationEntryPoint")
    AuthenticationEntryPoint customAuthenticationEntrypoint; 
    
    @Bean 
    @SuppressWarnings("java:S6437")
    public InMemoryUserDetailsManager userDetailService(PasswordEncoder passwordEndcoder){
                
        UserDetails titleUser = User.withUsername(ImdbSecurityConstants.USER_ROLE_TITLE)
            .password(passwordEndcoder.encode(ImdbSecurityConstants.PASS_ROLE_TITLE))
            .roles(ImdbSecurityConstants.ROLE_TITLE)
            .build(); 

        UserDetails degreeUser = User.withUsername(ImdbSecurityConstants.USER_ROLE_DEGREE)
            .password(passwordEndcoder.encode(ImdbSecurityConstants.PASS_ROLE_DEGREE))
            .roles(ImdbSecurityConstants.ROLE_DEGREE)
            .build(); 

        UserDetails nameUser = User.withUsername(ImdbSecurityConstants.USER_ROLE_NAME)
            .password(passwordEndcoder.encode(ImdbSecurityConstants.PASS_ROLE_NAME))
            .roles(ImdbSecurityConstants.ROLE_NAME)
            .build(); 

        UserDetails titleNameUser = User.withUsername(ImdbSecurityConstants.USER_ROLE_TITLE_NAME)
            .password(passwordEndcoder.encode(ImdbSecurityConstants.PASS_ROLE_TITLE_NAME))
            .roles(ImdbSecurityConstants.ROLE_TITLE, ImdbSecurityConstants.ROLE_NAME)
            .build(); 

        UserDetails titleNameDegreeUser = User.withUsername(ImdbSecurityConstants.USER_ROLE_TITLE_NAME_DEGREE)
            .password(passwordEndcoder.encode(ImdbSecurityConstants.PASS_ROLE_TITLE_NAME_DEGREE))
            .roles(ImdbSecurityConstants.ROLE_TITLE, ImdbSecurityConstants.ROLE_NAME, ImdbSecurityConstants.ROLE_DEGREE)
            .build(); 
            
        UserDetails noRoleUser = User.withUsername(ImdbSecurityConstants.USER_ROLE_NO)
            .password(passwordEndcoder.encode(ImdbSecurityConstants.PASS_ROLE_NO))
            .roles(ImdbSecurityConstants.ROLE_NO)
            .build();              

        return new InMemoryUserDetailsManager(titleUser, degreeUser, nameUser, titleNameUser, titleNameDegreeUser, noRoleUser); 
    }

    @Bean
    @SuppressWarnings("java:S125")
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
        http.authorizeHttpRequests()
            .requestMatchers(TITLE_URL_PATTERN).hasRole(ImdbSecurityConstants.ROLE_TITLE)
            .requestMatchers(NAME_URL_PATTERN).hasRole(ImdbSecurityConstants.ROLE_NAME)
            .requestMatchers(DEGREE_URL_PATTERN).hasRole(ImdbSecurityConstants.ROLE_DEGREE)
            .anyRequest()
            .authenticated()
            .and()
            .httpBasic();
            // .and()
            // .exceptionHandling()
            // .authenticationEntryPoint(customAuthenticationEntrypoint); 
        
        return http.build(); 
    }

    @Bean 
    public PasswordEncoder passwordEncoder(){
        return PasswordEncoderFactories.createDelegatingPasswordEncoder(); 
    }

}
