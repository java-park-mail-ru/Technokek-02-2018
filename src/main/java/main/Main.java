package main;


import main.dao.UserDao;
import main.dao.UserDaoSystem;
import main.properties.FileStorageProperties;
import main.service.UserService;
import main.service.avatars.AvatarControllerService;
import main.service.avatars.AvatarStorageService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;


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

    @Bean
    public JdbcTemplate getJdbcTemplate() {
        return new JdbcTemplate(getDataSource());
    }

    @Bean
    public DataSource getDataSource() {
        final DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setUrl("jdbc:postgresql://localhost:5432/technokek");
        dataSource.setUsername("technokek");
        dataSource.setPassword("technokek");
        dataSource.setDriverClassName("org.postgresql.Driver");
        return dataSource;
    }

    @Bean
    public UserDao getUserDao() {
        return new UserDaoSystem(getJdbcTemplate());
    }

    @Bean
    public UserService getUserService() {
        return new UserService();
    }

    @Bean
    public AvatarControllerService getAvatarControllerService() {
        return new AvatarControllerService();
    }

}
