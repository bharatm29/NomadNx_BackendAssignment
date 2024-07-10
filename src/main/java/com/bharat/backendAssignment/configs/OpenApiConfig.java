package com.bharat.backendAssignment.configs;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import lombok.AllArgsConstructor;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties(OpenApiConfigProperties.class)
@AllArgsConstructor
public class OpenApiConfig {
    private final OpenApiConfigProperties properties;

    private static final String BEARER_AUTH_COMPONENT_NAME = "Bearer Authentication";
    private static final String BEARER_AUTH_SCHEME = "Bearer";

    @Bean
    public OpenAPI openApi() {
        final var info = new Info()
                .version(properties.getVersion())
                .title(properties.getTitle());

        return new OpenAPI()
                .info(info)
                .components(new Components()
                        .addSecuritySchemes(BEARER_AUTH_COMPONENT_NAME,
                                new SecurityScheme()
                                        .type(SecurityScheme.Type.HTTP)
                                        .scheme(BEARER_AUTH_SCHEME)))
                .addSecurityItem(new SecurityRequirement()
                        .addList(BEARER_AUTH_COMPONENT_NAME));
    }
}
