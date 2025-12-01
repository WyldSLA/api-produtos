package com.wyldSLA.produtos.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringDoc {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("API de ProdutoModel") // Título da API
                        .description("API REST para gerenciar produtos. Permite criar, atualizar, listar e remover produtos.")
                        .version("1.0.0")
                        .contact(new Contact()
                                .name("WyldSla")
                                .email("wyldsonmarllon39@gmail.com"))
                        .license(new License()
                                .name("MIT License") // Nome da licença
                                .url("https://opensource.org/licenses/MIT")));
    }
}