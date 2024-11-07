package com.example.demo;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import com.example.demo.services.impl.BycryptPassswordEncoder;
import com.example.demo.services.impl.DefaultJWTService;
// import com.example.demo.services.impl.DefaultUserImplementation;
import com.example.demo.services.impl.EncodedUserImplementation;
import com.example.demo.dto.Token;
import com.example.demo.services.JWTService;
import com.example.demo.services.PasswordEncoderService;
import com.example.demo.services.UserService;

@Configuration
public class DependencyConfiguration {
    
    @Bean
    @Scope("singleton") // prototype:   cria um obj novo a cada requisição 
                        // request:     existe durante a requisição, caso precise de um mesmo obj ele reutiliza na requisição
                        // session:      cria um obejto para cada usuário

                        // serviço User e serviço Product dependem de um serviço database (Context por exemplo), use o request

    public UserService userSerice() {
        // return new DefaultUserImplementation(); // for C6 exercise without security 
        return new EncodedUserImplementation(); // for C8 and beyond 
    } 

    @Bean
    public PasswordEncoderService passwordEncoder() {
        return new BycryptPassswordEncoder();
    }

    @Bean
    public JWTService<Token> jwtService() {
        return new DefaultJWTService();
    }

}
