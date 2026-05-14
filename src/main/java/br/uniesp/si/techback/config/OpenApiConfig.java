package br.uniesp.si.techback.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("TecBackFULL API")
                        .version("1.0.0")
                        .description("Backend completo - Disciplina Backend 1 Uniesp. " +
                                "CRUD de Filmes, Categorias, Endereços, Funcionários, " +
                                "Produtos, Usuários, Pedidos, Itens de Pedido e Pagamentos.")
                        .contact(new Contact()
                                .name("Uniesp - Backend 1")
                                .email("backend@uniesp.edu.br")));
    }
}
