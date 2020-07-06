package com.example.demo.config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;


import javax.sql.DataSource;
import java.util.Properties;
import java.util.UUID;

@Configuration
@EnableJpaRepositories(
        basePackages = {"com.example.demo.dao"},
        entityManagerFactoryRef = "entityManagerFactory",
        transactionManagerRef = "transactionManager")
@EnableTransactionManagement
@EnableJpaAuditing
public class JpaConfig {

    @Autowired
    private Settings settings;

    private static HikariConfig config = new HikariConfig();

    @Bean(name = "transactionManager")
    @Primary
    public PlatformTransactionManager transactionManager() {
        return new JpaTransactionManager(entityManagerFactory().getObject());
    }

    @Bean(name = "entityManagerFactory")
    @Primary
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {

        HibernateJpaVendorAdapter jpaVendorAdapter = new HibernateJpaVendorAdapter();

        LocalContainerEntityManagerFactoryBean factoryBean = new LocalContainerEntityManagerFactoryBean();
        factoryBean.setDataSource(dataSource());
        factoryBean.setJpaVendorAdapter(jpaVendorAdapter);
        factoryBean.setJpaProperties(hibernateProperties());

        factoryBean.setPackagesToScan("com.example.demo.domain");
        factoryBean.setPersistenceUnitName("demoDb");
        factoryBean.afterPropertiesSet();
        return factoryBean;
    }

    @Primary
    @Bean(name = "dataSource")
    public DataSource dataSource() {
        config.setJdbcUrl(settings.getDbUrl());
        config.setUsername(settings.getUserName());
        config.setPassword(settings.getDbPassword());
        config.setDriverClassName(settings.getDbDriver());
        config.setConnectionTimeout(settings.getHikariConnectionTimeout());
        config.setAutoCommit(settings.isHikariAutoCommit());
        config.setIdleTimeout(settings.getHikariIdleTimeout());
        config.setMaximumPoolSize(settings.getHikariMaximumPoolSize());
        config.setMinimumIdle(settings.getHikariMinimumIdle());
        config.setMaxLifetime(settings.getDbMaxLifeTime());
        config.setLeakDetectionThreshold(settings.getHikariLeakThreshold());

        // config.addDataSourceProperty( "cachePrepStmts" , settings.isHikariCachePrepStmts() );
        // config.addDataSourceProperty( "prepStmtCacheSize" , settings.getHikariPrepStmtCacheSize() );
        // config.addDataSourceProperty( "prepStmtCacheSqlLimit" , settings.getHikariPrepStmtCacheSqlLimit() );
        return new HikariDataSource(config);
    }

    private Properties hibernateProperties() {
        final Properties hibernateProperties = new Properties();
        hibernateProperties.setProperty("hibernate.dialect", settings.getPostgreSQLDialect());
        hibernateProperties.setProperty("hibernate.hbm2ddl.auto", "validate");
        // check release mode in production
        hibernateProperties.setProperty("hibernate.connection.release_mode", "after_transaction");

        // uncomment if need show log db statistic hibernate.generate_statistics
        hibernateProperties.setProperty("hibernate.current_session_context_class", "org.springframework.orm.hibernate5.SpringSessionContext");
        hibernateProperties.setProperty("hibernate.connection.provider_disables_autocommit", "true");

        return hibernateProperties;
    }

    @Bean
    AuditorAware<UUID> auditorProvider() {
        return new SpringSecurityAuditorAware();
    }
}
