package com.santanna.olympicgames.infra;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringDocsConfig {

    @Bean
    public OpenAPI customOpenApi() {
        return new OpenAPI()
                .info(new Info()
                        .title("Olympic Games")
                        .description("Olympic Games Rest API, with CRUD Athletes features")
                        .contact(new Contact()
                                .name("Sant'Anna, Lucas")
                                .email("lucasnb06@gmail.com"))
                );
    }
}
