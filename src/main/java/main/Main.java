package main;


import main.properties.FileStorageProperties;
import main.service.avatars.AvatarStorageService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;


@SpringBootApplication
@EnableConfigurationProperties(FileStorageProperties.class)
public class Main {

    public static void main(String[] args) {

        SpringApplication.run(Main.class, args);
    }

    @Bean
    CommandLineRunner init(AvatarStorageService storageService) {
        return (args) -> {
            storageService.init();
        };
    }

}
