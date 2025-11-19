package com.accenture.excusas.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Configuración de Swagger/OpenAPI 3 para documentación automática de la API.
 * 
 * Genera documentación interactiva disponible en:
 * - Swagger UI: http://localhost:8080/swagger-ui.html
 * - OpenAPI JSON: http://localhost:8080/v3/api-docs
 * - OpenAPI YAML: http://localhost:8080/v3/api-docs.yaml
 */
@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Excusas Tech API")
                        .version("1.0.0")
                        .description("API generadora de excusas técnicas aleatorias con fragmentos combinables, " +
                                "memes argentinos y leyes/axiomas (Murphy, Hofstadter, Dilbert, DevOps Principles)")
                        .contact(new Contact()
                                .name("Accenture Development Team")
                                .url("https://www.accenture.com"))
                        .license(new License()
                                .name("MIT License")
                                .url("https://opensource.org/licenses/MIT")))
                .addServersItem(new Server()
                        .url("http://localhost:8080")
                        .description("Local Development Server"))
                .addServersItem(new Server()
                        .url("http://api.example.com")
                        .description("Production Server"));
    }
}
