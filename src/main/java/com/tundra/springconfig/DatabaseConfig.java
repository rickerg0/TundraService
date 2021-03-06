package com.tundra.springconfig;

import java.util.Properties;

import javax.persistence.EntityManagerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaDialect;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.HibernateJpaDialect;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.zaxxer.hikari.HikariDataSource;

@Configuration
@EnableTransactionManagement
@ComponentScan("com.tundra")
@PropertySource("classpath:tundra.properties")
@EnableJpaRepositories(entityManagerFactoryRef="tundraEntityManagerFactory", 
                       transactionManagerRef="tundraTransactionManager",
                       basePackages="com.tundra.dao")

public class DatabaseConfig {
	
	@Value("${database.url}") private String url;
	@Value("${database.username}") private String username;
	@Value("${database.password}") private String password;
	
   @Bean(name="tundraHakariCPDatSource")
   public HikariDataSource tundraHikariDataSource() {
    HikariDataSource ds = new HikariDataSource();;
    ds.setDriverClassName("com.mysql.jdbc.Driver");
    ds.setJdbcUrl(url);
    ds.setUsername(username);
    ds.setPassword(password);
    ds.setConnectionTestQuery("select 1");
    ds.setConnectionTimeout(1000);
    ds.setMaximumPoolSize(1);
    ds.setPoolName("tundraDatabaseConnectionPool");
    return ds;
   }
          
   @Bean(name="tundraEntityManagerFactory")
   @Autowired
   public EntityManagerFactory tundraEntityManagerFactory() {
    HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
    vendorAdapter.setGenerateDdl(true);
    vendorAdapter.setShowSql(false);
    vendorAdapter.setDatabasePlatform("org.hibernate.dialect.MySQL5Dialect");
    vendorAdapter.setDatabase(Database.MYSQL);

    LocalContainerEntityManagerFactoryBean factory = new LocalContainerEntityManagerFactoryBean();
    factory.setJpaVendorAdapter(vendorAdapter);
    factory.setPackagesToScan("com.tundra.entity");
    factory.setDataSource(tundraHikariDataSource());

    Properties properties = new Properties();
  //  properties.setProperty("hibernate.cache.use_second_level_cache", "true");
  //  properties.setProperty("hibernate.cache.region.factory_class", "org.hibernate.cache.ehcache.EhCacheRegionFactory");
 //   properties.setProperty("hibernate.cache.use_query_cache", "true");
    properties.setProperty("hibernate.generate_statistics", "false");
    properties.setProperty("hibernate.show_sql", "false");
    properties.setProperty("hibernate.format_sql", "false");
    properties.setProperty("hibernate.hbm2ddl.auto", "validate");
    properties.setProperty("hibernate.default_schema", "tundra");

    factory.setJpaProperties(properties);

    factory.afterPropertiesSet();

    return factory.getObject();
   }

   @Bean
   @Autowired
   public JpaTransactionManager tundraTransactionManager() {
    JpaTransactionManager txManager = new JpaTransactionManager();
    JpaDialect jpaDialect = new HibernateJpaDialect();
    txManager.setEntityManagerFactory(tundraEntityManagerFactory());
    txManager.setJpaDialect(jpaDialect);
    return txManager;
   }
}
