package com.legacy;

import com.legacy.config.AppConfig;
import com.legacy.model.Empresa;
import com.legacy.service.EmpresaService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(AppConfig.class);

        EmpresaService empresaService = context.getBean(EmpresaService.class);

        Empresa empresa = new Empresa("Tech Solutions", "Tech Solutions LTDA", "12345678000190");
        empresaService.registrarEmpresa(empresa);

        Empresa encontrada = empresaService.buscarEmpresa(empresa.getId());
        System.out.println("Empresa encontrada: " + encontrada.getNomeFantasia());
    }
}
