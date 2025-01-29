package com.example.ad_entrega9_apihoteljwt.Config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfiguration {
    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .components(new Components())
                .info(new Info().title("ApiHotelLeire")
                        .description("API de Hoteles y Habitaciones")
                        .contact(new Contact().name("Leire").email("leire.yagfer.1@educa.jcyl.es"))
                        .version("1.0"));
    }
}
