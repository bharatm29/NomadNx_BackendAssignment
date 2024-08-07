package com.bharat.backendAssignment.configs;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
@ConfigurationProperties(prefix = "firebase")
public class FirebaseConfigProperties {
    private String privateKeySrc;
    private String webKey;
    private String webUrl;
}
