package com.mssql_sdk.openlegacy.config;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.openlegacy.core.beans.DbBeanNames;
import org.openlegacy.impl.properties.OLDatabaseProperties;
import org.openlegacy.impl.properties.OLDatabaseProperties.OLDatabaseProjectProperties;
import org.openlegacy.impl.utils.OLJdbcBeanUtils;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;

/**
 * JDBC Configuration
 */
@Configuration
@EnableJpaRepositories(
        entityManagerFactoryRef = MssqlSdkConfiguration.DATASOURCE_KEY + DbBeanNames.ENTITIES_REGISTRY_MANAGER_SUFFIX,
        transactionManagerRef = MssqlSdkConfiguration.DATASOURCE_KEY + DbBeanNames.TRANSACTION_MANAGER_SUFFIX,
        basePackages = { "com.mssql_sdk.openlegacy.repositories" })
public class MssqlSdkConfiguration {
    protected static final String DATASOURCE_KEY = "mssqlSdk";
    private static final String[] packagesToScan = new String[] {"com.mssql_sdk.openlegacy", "com.mssql_sdk.openlegacy.entities"};

    private OLDatabaseProjectProperties olDatabaseProjectProperties;

    public MssqlSdkConfiguration(OLDatabaseProperties olDatabaseProperties) {
        this.olDatabaseProjectProperties = OLJdbcBeanUtils.getProjectProperties(olDatabaseProperties, DATASOURCE_KEY);
    }

    @Bean(DATASOURCE_KEY + DbBeanNames.DATA_SOURCE_SUFFIX)
    public DataSource dataSource() {
        return OLJdbcBeanUtils.dataSource(olDatabaseProjectProperties);
    }

    @Bean(DATASOURCE_KEY + DbBeanNames.ENTITIES_REGISTRY_MANAGER_SUFFIX)
    public LocalContainerEntityManagerFactoryBean entityManagerFactoryBean(
            @Qualifier(DATASOURCE_KEY + DbBeanNames.DATA_SOURCE_SUFFIX) DataSource dataSource) {
        return OLJdbcBeanUtils.entityManagerFactoryBean(dataSource, olDatabaseProjectProperties, packagesToScan);
    }

    @Bean(DATASOURCE_KEY + DbBeanNames.TRANSACTION_MANAGER_SUFFIX)
    public PlatformTransactionManager transactionManager(
            @Qualifier(DATASOURCE_KEY + DbBeanNames.ENTITIES_REGISTRY_MANAGER_SUFFIX) EntityManagerFactory entityManagerFactory) {
        return OLJdbcBeanUtils.transactionManager(entityManagerFactory);
    }

}

