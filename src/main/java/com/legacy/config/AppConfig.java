package com.legacy.config;

import com.legacy.dao.EmpresaDAO;
import com.legacy.service.EmpresaService;
import org.hibernate.SessionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.orm.hibernate4.HibernateTransactionManager;
import org.springframework.orm.hibernate4.LocalSessionFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.Properties;

@Configuration
@EnableTransactionManagement
public class AppConfig {

    @Bean
    public DataSource dataSource() {
        return new EmbeddedDatabaseBuilder()
                .setType(EmbeddedDatabaseType.H2)
                .build();
    }

    @Bean
    public LocalSessionFactoryBean sessionFactory(DataSource dataSource) {
        LocalSessionFactoryBean sessionFactoryBean = new LocalSessionFactoryBean();
        sessionFactoryBean.setDataSource(dataSource);
        sessionFactoryBean.setPackagesToScan("com.legacy.model");
        Properties props = new Properties();
        props.put("hibernate.dialect", "org.hibernate.dialect.H2Dialect");
        props.put("hibernate.hbm2ddl.auto", "create-drop");
        props.put("hibernate.show_sql", "true");
        sessionFactoryBean.setHibernateProperties(props);
        return sessionFactoryBean;
    }

    @Bean
    public PlatformTransactionManager transactionManager(SessionFactory sessionFactory) {
        return new HibernateTransactionManager(sessionFactory);
    }

    @Bean
    public EmpresaDAO empresaDAO(SessionFactory sessionFactory) {
        EmpresaDAO dao = new EmpresaDAO();
        dao.setSessionFactory(sessionFactory);
        return dao;
    }

    @Bean
    public EmpresaService empresaService(EmpresaDAO empresaDAO) {
        EmpresaService service = new EmpresaService();
        service.setEmpresaDAO(empresaDAO);
        return service;
    }
}

