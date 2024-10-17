package lk.ijse.web_poss_backend.config;

import jakarta.persistence.EntityManagerFactory;
import jakarta.servlet.annotation.MultipartConfig;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import javax.sql.DataSource;

@Configuration
@ComponentScan(basePackages = "lk.ijse.web_poss_backend")
@EnableJpaRepositories(basePackages = "lk.ijse.web_poss_backend")
@EnableTransactionManagement
public class WebAppRootConfig {

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

    @Bean
    public DataSource dataSource() {

        var dmds = new DriverManagerDataSource();
        dmds.setDriverClassName("com.mysql.cj.jdbc.Driver");
        dmds.setUrl("jdbc:mysql://localhost:3306/web_poss?createDatabaseIfNotExist=true");
        dmds.setUsername("root");
        dmds.setPassword("Ijse@1234");
        return dmds;
    }

    //orm tool connect
    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {

        HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        vendorAdapter.setGenerateDdl(true);

        LocalContainerEntityManagerFactoryBean factory = new LocalContainerEntityManagerFactoryBean();
        factory.setJpaVendorAdapter(vendorAdapter);
        factory.setPackagesToScan("lk.ijse.web_poss_backend.entity");
        factory.setDataSource(dataSource());
        return factory;
    }



    @Bean
    public PlatformTransactionManager transactionManager(EntityManagerFactory entityManagerFactory) {

        JpaTransactionManager txManager = new JpaTransactionManager();
        txManager.setEntityManagerFactory(entityManagerFactory);
        return txManager;
    }
}

