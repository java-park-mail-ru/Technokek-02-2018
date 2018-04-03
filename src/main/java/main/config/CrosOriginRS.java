package main.config;

import main.properties.FileStorageProperties;
import main.service.avatars.AvatarStorageService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@SpringBootConfiguration
@EnableConfigurationProperties(FileStorageProperties.class)
public class CrosOriginRS {
    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurerAdapter() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**").allowedOrigins("https://teckokek-front.herokuapp.com/")
                        .allowCredentials(true)
                        .allowedMethods("GET", "POST");
            }
        };
    }

    @Bean
    CommandLineRunner init(AvatarStorageService storageService) {
        return (args) -> {
            storageService.init();
        };
    }
}
