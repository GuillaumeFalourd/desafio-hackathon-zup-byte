package com.legacy;

import com.legacy.config.AppConfig;
import com.legacy.dao.EmpresaDAO;
import com.legacy.service.EmpresaService;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class AppConfigTest {

    @Test
    public void shouldLoadBeansFromConfig() {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);

        EmpresaDAO empresaDAO = context.getBean(EmpresaDAO.class);
        EmpresaService empresaService = context.getBean(EmpresaService.class);

        Assert.assertNotNull(empresaDAO);
        Assert.assertNotNull(empresaService);

        context.close();
    }
}