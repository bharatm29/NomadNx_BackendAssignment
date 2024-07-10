package com.bharat.backendAssignment.configs;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "open-api")
@Data
public class OpenApiConfigProperties {
    private String title;
    private String version;
}
