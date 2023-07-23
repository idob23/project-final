package com.javarush.jira;

import org.junit.jupiter.api.extension.BeforeAllCallback;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.testcontainers.containers.PostgreSQLContainer;

public class TestContainerSetup implements BeforeAllCallback, ExtensionContext.Store.CloseableResource{
//TODO - 4.TestContainer SetUp
    private static PostgreSQLContainer<?> postgresContainer;

    @Override
    public void beforeAll(ExtensionContext extensionContext) {
        if (postgresContainer == null) {
            postgresContainer = new PostgreSQLContainer<>("postgres");
            postgresContainer.start();
        }
        System.setProperty("spring.datasource.url", postgresContainer.getJdbcUrl());
        System.setProperty("spring.datasource.username", postgresContainer.getUsername());
        System.setProperty("spring.datasource.password", postgresContainer.getPassword());
    }

    @Override
    public void close() {
        if (postgresContainer != null) {
            postgresContainer.stop();
        }
    }

}