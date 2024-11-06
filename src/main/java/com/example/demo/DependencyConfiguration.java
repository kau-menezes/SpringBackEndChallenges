package com.example.demo;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

@Configuration
public class DependencyConfiguration {
    
    @Bean
    @Scope("singleton") // prototype:   cria um obj novo a cada requisição 
                        // request:     existe durante a requisição, caso precise de um mesmo obj ele reutiliza na requisição
                        // session:      cria um obejto para cada usuário

                        // serviço User e serviço Product dependem de um serviço database (Context por exemplo), use o request

    public void service() {} // add dependency here

}
