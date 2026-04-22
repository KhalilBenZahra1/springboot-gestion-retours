package com.gestion.gestionretours.config;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI gestionRetoursOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("API - Système de gestion des retours")
                        .description("Documentation de l'API REST du projet de gestion des retours produits, non-conformités, utilisateurs, historique et stock.")
                        .version("1.0.0")
                        .contact(new Contact()
                                .name("Projet Spring Boot / Angular")
                                .email("contact@example.com")))
                .externalDocs(new ExternalDocumentation()
                        .description("Documentation Swagger")
                        .url("http://localhost:8080/swagger-ui/index.html"));
    }
}