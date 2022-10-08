package mj.api.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
        basePackages = {"mj.api.domain"},
        entityManagerFactoryRef = "apiEntityManagerFactory",
        transactionManagerRef = "apiTransactionManager"
)
public class ApiJpaConfiguration {
    @Bean
    @ConfigurationProperties("spring.datasource")
    public DataSourceProperties apiDataSourceProperties() {
        return new DataSourceProperties();
    }

    @Bean(name = "apiDataSource")
    public DataSource dataSource() {
        return apiDataSourceProperties()
                .initializeDataSourceBuilder()
                .build();
    }

    @Bean(name = "apiEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(
            EntityManagerFactoryBuilder builder, @Qualifier("apiDataSource") DataSource dataSource) {
        return builder
                .dataSource(dataSource)
                .packages("mj.api.domain")
                .build();
    }

    @Bean(name = "apiTransactionManager")
    public PlatformTransactionManager transactionManager(
            @Qualifier("apiEntityManagerFactory") EntityManagerFactory entityManagerFactory) {
        return new JpaTransactionManager(entityManagerFactory);
    }
}
