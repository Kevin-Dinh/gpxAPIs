package com.example.demo.config;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Settings implements InitializingBean {

    @Value("${spring.datasource.jdbc-url}")
    private String dbUrl;

    @Value("${spring.datasource.password}")
    private String dbPassword;

    @Value("${spring.datasource.username}")
    private String userName;

    @Value("${spring.datasource.driver-class-name:org.postgresql.Driver}")
    private String dbDriver;

    @Value("${demo.hikari.connection-timeout:35000}")
    private long hikariConnectionTimeout;

    @Value("${spring.datasource.hikari.auto-commit:false}")
    private boolean hikariAutoCommit;

    @Value("${spring.datasource.hikari.idle-timeout:20000}")
    private int hikariIdleTimeout;

    @Value("${spring.datasource.hikari.maximum-pool-size:20}")
    private int hikariMaximumPoolSize;

    @Value("${spring.datasource.hikari.minimum-idle:0}")
    private int hikariMinimumIdle;

    @Value("${demo.db-connection.max-life-time:1800000}")
    private int dbMaxLifeTime;

    @Value("${demo.hikari.leak.detection.threashold.leak:20000}")
    private long hikariLeakThreshold;

    @Value("${spring.jpa.properties.hibernate.dialect:org.hibernate.dialect.PostgreSQLDialect}")
    private String postgreSQLDialect;

    @Override
    public void afterPropertiesSet() throws Exception {}

    public String getDbUrl() {
        return dbUrl;
    }

    public void setDbUrl(String dbUrl) {
        this.dbUrl = dbUrl;
    }

    public String getDbPassword() {
        return dbPassword;
    }

    public void setDbPassword(String dbPassword) {
        this.dbPassword = dbPassword;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getDbDriver() {
        return dbDriver;
    }

    public void setDbDriver(String dbDriver) {
        this.dbDriver = dbDriver;
    }

    public long getHikariConnectionTimeout() {
        return hikariConnectionTimeout;
    }

    public void setHikariConnectionTimeout(long hikariConnectionTimeout) {
        this.hikariConnectionTimeout = hikariConnectionTimeout;
    }

    public boolean isHikariAutoCommit() {
        return hikariAutoCommit;
    }

    public void setHikariAutoCommit(boolean hikariAutoCommit) {
        this.hikariAutoCommit = hikariAutoCommit;
    }

    public int getHikariIdleTimeout() {
        return hikariIdleTimeout;
    }

    public void setHikariIdleTimeout(int hikariIdleTimeout) {
        this.hikariIdleTimeout = hikariIdleTimeout;
    }

    public int getHikariMaximumPoolSize() {
        return hikariMaximumPoolSize;
    }

    public void setHikariMaximumPoolSize(int hikariMaximumPoolSize) {
        this.hikariMaximumPoolSize = hikariMaximumPoolSize;
    }

    public int getHikariMinimumIdle() {
        return hikariMinimumIdle;
    }

    public void setHikariMinimumIdle(int hikariMinimumIdle) {
        this.hikariMinimumIdle = hikariMinimumIdle;
    }

    public int getDbMaxLifeTime() {
        return dbMaxLifeTime;
    }

    public void setDbMaxLifeTime(int dbMaxLifeTime) {
        this.dbMaxLifeTime = dbMaxLifeTime;
    }

    public long getHikariLeakThreshold() {
        return hikariLeakThreshold;
    }

    public void setHikariLeakThreshold(long hikariLeakThreshold) {
        this.hikariLeakThreshold = hikariLeakThreshold;
    }

    public String getPostgreSQLDialect() {
        return postgreSQLDialect;
    }

    public void setPostgreSQLDialect(String postgreSQLDialect) {
        this.postgreSQLDialect = postgreSQLDialect;
    }
}
