package com.example.diplomaprojectgeneticcode.repo.config;

import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class DataSourceConfig {

    private static final String USERNAME = "postgres";
    private static final String URL = "jdbc:postgresql://localhost:5432/qcrm?stringtype=unspecified";
    private static final String PASSWORD = "123";
    private static final String DRIVER = "org.postgresql.Driver";

    @Bean
    public DataSource dataSource() {
        return DataSourceBuilder.create()
                .driverClassName(DRIVER)
                .url(URL)
                .username(USERNAME)
                .password(PASSWORD)
                .build();
    }

//    @Bean
//    public DataSourceInitializer dataSourceInitializer(@Qualifier("dataSource") final DataSource dataSource) {
//        ResourceDatabasePopulator resourceDatabasePopulator = new ResourceDatabasePopulator();
//        resourceDatabasePopulator.addScript(new ClassPathResource("/schema.sql"));
//        DataSourceInitializer dataSourceInitializer = new DataSourceInitializer();
//        dataSourceInitializer.setDataSource(dataSource);
//        dataSourceInitializer.setDatabasePopulator(resourceDatabasePopulator);
//        return dataSourceInitializer;
//    }
}
