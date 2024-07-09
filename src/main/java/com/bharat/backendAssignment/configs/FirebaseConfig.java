package com.bharat.backendAssignment.configs;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.FileInputStream;

@Configuration
@RequiredArgsConstructor
@EnableConfigurationProperties(FirebaseConfigProperties.class)
public class FirebaseConfig {
    private final FirebaseConfigProperties firebaseConfigProperties;

    @Bean
    @SneakyThrows
    public FirebaseApp firebaseApp() {
        FileInputStream serviceAccount = new FileInputStream(firebaseConfigProperties.getPrivateKeySrc());

        final var firebaseOptions = FirebaseOptions.builder()
                .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                .build();

        return FirebaseApp.initializeApp(firebaseOptions);
    }
}