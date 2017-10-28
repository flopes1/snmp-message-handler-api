package com.poli.snmp.config;

import java.util.Properties;

import javax.sql.DataSource;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.poli.snmp.config.properties.PropertiesLoaderConstants;

@Configuration
@EnableTransactionManagement
@ComponentScan(
{ "com.poli.snmp.config", "com.poli.snmp.dao", "com.poli.snmp.model" })
@PropertySource(value =
{ "classpath:" + PropertiesLoaderConstants.PROPERTIES_FILE_NAME })
public class DatabaseConfiguration
{

    @Autowired
    private Environment environment;

    @Bean
    public LocalSessionFactoryBean sessionFactory()
    {
        LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
        sessionFactory.setDataSource(dataSource());
        sessionFactory.setPackagesToScan(new String[]
        { "com.poli.snmp.model", "com.poli.snmp.dao" });
        sessionFactory.setHibernateProperties(hibernateProperties());
        return sessionFactory;
    }

    @Bean
    public DataSource dataSource()
    {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(environment.getRequiredProperty(PropertiesLoaderConstants.DATABASE_DRIVER));
        dataSource.setUrl(environment.getRequiredProperty(PropertiesLoaderConstants.DATABASE_ADDRESS));
        dataSource.setUsername(environment.getRequiredProperty(PropertiesLoaderConstants.DATABASE_USER));
        dataSource.setPassword(environment.getRequiredProperty(PropertiesLoaderConstants.DATABASE_PASSWORD));
        // dataSource.setSchema(environment.getRequiredProperty(PropertiesLoaderConstants.DATABASE_NAME));
        return dataSource;
    }

    private Properties hibernateProperties()
    {
        Properties properties = new Properties();
        properties.put(PropertiesLoaderConstants.HIBERNATE_DIALECT,
                environment.getRequiredProperty(PropertiesLoaderConstants.HIBERNATE_DIALECT));
        properties.put(PropertiesLoaderConstants.HIBERNATE_SHOW_SQL,
                environment.getRequiredProperty(PropertiesLoaderConstants.HIBERNATE_SHOW_SQL));
        properties.put(PropertiesLoaderConstants.HIBERNATE_FORMAT_SQL,
                environment.getRequiredProperty(PropertiesLoaderConstants.HIBERNATE_FORMAT_SQL));

        return properties;
    }

    @Bean
    public HibernateTransactionManager transactionManager(SessionFactory sessionFactory)
    {
        HibernateTransactionManager txManager = new HibernateTransactionManager();
        txManager.setSessionFactory(sessionFactory);
        return txManager;
    }

}
