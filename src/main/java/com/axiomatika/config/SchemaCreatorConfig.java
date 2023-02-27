package com.axiomatika.config;

import liquibase.integration.spring.SpringLiquibase;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.liquibase.LiquibaseProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;

import javax.sql.DataSource;

@Configuration
@RequiredArgsConstructor
@EnableConfigurationProperties(LiquibaseProperties.class)
public class SchemaCreatorConfig {

    private final DataSource dataSource;
    private final LiquibaseProperties liquibaseProperties;

    @Bean
    @DependsOn("schemaCreatorService")
    public SpringLiquibase liquibase() {
        final SpringLiquibase springLiquibase = new SpringLiquibase();
        springLiquibase.setDataSource(dataSource);
        springLiquibase.setContexts(liquibaseProperties.getContexts());
        springLiquibase.setDropFirst(liquibaseProperties.isDropFirst());
        springLiquibase.setChangeLog(liquibaseProperties.getChangeLog());
        springLiquibase.setRollbackFile(liquibaseProperties.getRollbackFile());
        springLiquibase.setDefaultSchema(liquibaseProperties.getDefaultSchema());
        springLiquibase.setChangeLogParameters(liquibaseProperties.getParameters());
        springLiquibase.setLiquibaseSchema(liquibaseProperties.getLiquibaseSchema());
        springLiquibase.setLiquibaseTablespace(liquibaseProperties.getLiquibaseTablespace());
        springLiquibase.setDatabaseChangeLogTable(liquibaseProperties.getDatabaseChangeLogTable());
        springLiquibase.setDatabaseChangeLogLockTable(liquibaseProperties.getDatabaseChangeLogLockTable());

        return springLiquibase;
    }

    @Bean("schemaCreatorService")
    public SchemaCreatorService schemaCreatorService() {
        return new SchemaCreatorService(dataSource, liquibaseProperties);
    }
}
