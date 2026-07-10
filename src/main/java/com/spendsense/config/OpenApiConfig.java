package com.spendsense.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    private static final String SECURITY_SCHEME_NAME = "Bearer Authentication";

    @Bean
    public OpenAPI openAPI() {

        return new OpenAPI()

                .info(new Info()

                        .title("SpendSense API")

                        .version("v1.0.0")

                        .description("""
                                AI Powered Personal Finance Platform

                                Features:
                                • JWT Authentication
                                • Expense Management
                                • Category Management
                                • Budget Planning
                                • Investment Tracking
                                • AI Financial Assistant
                                """)

                        .contact(new Contact()

                                .name("Karan Sandhu")

                                .email("your-email@example.com")

                                .url("https://github.com/Karan16-dot"))

                        .license(new License()

                                .name("Apache 2.0")

                                .url("https://www.apache.org/licenses/LICENSE-2.0")))

                .addSecurityItem(new SecurityRequirement()

                        .addList(SECURITY_SCHEME_NAME))

                .components(new Components()

                        .addSecuritySchemes(

                                SECURITY_SCHEME_NAME,

                                new SecurityScheme()

                                        .name(SECURITY_SCHEME_NAME)

                                        .type(SecurityScheme.Type.HTTP)

                                        .scheme("bearer")

                                        .bearerFormat("JWT")));
    }

}