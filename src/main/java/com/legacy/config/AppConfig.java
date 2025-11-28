package com.legacy.config;

import com.legacy.dao.EmpresaDAO;
import com.legacy.service.EmpresaService;
import org.hibernate.SessionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.hibernate4.LocalSessionFactoryBean;

import java.util.Properties;

@Configuration
public class AppConfig {

    @Bean
    public LocalSessionFactoryBean sessionFactory() {
        LocalSessionFactoryBean sessionFactoryBean = new LocalSessionFactoryBean();
        sessionFactoryBean.setPackagesToScan("com.legacy.model");
        Properties props = new Properties();
        props.put("hibernate.dialect", "org.hibernate.dialect.H2Dialect");
        props.put("hibernate.hbm2ddl.auto", "create-drop");
        props.put("hibernate.show_sql", "true");
        sessionFactoryBean.setHibernateProperties(props);
        return sessionFactoryBean;
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
