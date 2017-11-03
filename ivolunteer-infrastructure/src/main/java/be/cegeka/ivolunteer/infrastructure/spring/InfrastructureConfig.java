package be.cegeka.ivolunteer.infrastructure.spring;

import org.apache.tomcat.jdbc.pool.PoolConfiguration;
import org.apache.tomcat.jdbc.pool.PoolProperties;
import org.hibernate.dialect.PostgreSQL9Dialect;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.orm.hibernate4.HibernateExceptionTranslator;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.util.Properties;

import static org.springframework.context.annotation.FilterType.ANNOTATION;

@Configuration
@ComponentScan(
        value = "be.cegeka.ivolunteer.infrastructure",
        excludeFilters = @ComponentScan.Filter(type = ANNOTATION, value = Configuration.class))
public class InfrastructureConfig {
    private static final boolean SHOW_SQL = true;

    @Bean
    public HibernateExceptionTranslator hibernateExceptionTranslator() {
        return new HibernateExceptionTranslator();
    }

    @Bean
    public PlatformTransactionManager transactionManager() {
        JpaTransactionManager txManager = new JpaTransactionManager();
        txManager.setEntityManagerFactory(entityManagerFactory());
        return txManager;
    }

    @Bean
    public EntityManagerFactory entityManagerFactory() {
        LocalContainerEntityManagerFactoryBean factory = new LocalContainerEntityManagerFactoryBean();
        factory.setDataSource(dataSource());
        factory.setJpaVendorAdapter(jpaVendorAdapter());
        factory.setPackagesToScan("be.cegeka.ivolunteer.event");
        factory.setJpaProperties(jpaProperties());
        factory.afterPropertiesSet();
        return factory.getObject();
    }

    @Bean
    public DataSource dataSource() {
//        if (isJndiDataSource()) {
//            LOGGER.info("Using jndi datasource '" + jndiDataSourceName() +"'");
//            return jndiDatasource();
//        } else {
//            LOGGER.info("Using local datasource");
            return localDatasource();
//        }
    }

    @Bean
    public NamedParameterJdbcTemplate namedParameterJdbcTemplate() {
        return new NamedParameterJdbcTemplate(dataSource());
    }

    @Bean
    public JdbcTemplate jdbcTemplate() {
        return new JdbcTemplate(dataSource());
    }

    private JpaVendorAdapter jpaVendorAdapter() {
        HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        vendorAdapter.setDatabasePlatform(PostgreSQL9Dialect.class.getName());
        vendorAdapter.setGenerateDdl(false);
        vendorAdapter.setShowSql(SHOW_SQL);
        return vendorAdapter;
    }

    private Properties jpaProperties() {
        Properties properties = new Properties();
        properties.put("hibernate.format_sql", true);
        properties.put("hibernate.default_schema", "public");
        properties.put("org.hibernate.envers.default_schema", "public");
        return properties;
    }

//    private boolean isJndiDataSource() {
//        return ofNullable(jndiDataSourceName()).isPresent();
//    }

//    private DataSource jndiDatasource() {
//        JndiDataSourceLookup jndiDataSourceLookup = new JndiDataSourceLookup();
//        return jndiDataSourceLookup.getDataSource(jndiDataSourceName());
//    }

//    private String jndiDataSourceName() {
//        return environment.getProperty("spring.datasource.jndi-name");
//    }

    private DataSource localDatasource() {
        PoolConfiguration poolConfiguration = new PoolProperties();
        poolConfiguration.setDriverClassName("org.postgresql.Driver");
        poolConfiguration.setUrl("jdbc:postgresql://localhost:5432/ivolunteer");
        poolConfiguration.setUsername("ivolunteer_user");
        poolConfiguration.setPassword("IVOLUNTEER");
        return new org.apache.tomcat.jdbc.pool.DataSource(poolConfiguration);
    }

}
