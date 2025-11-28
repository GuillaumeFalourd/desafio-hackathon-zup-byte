package com.legacy.model;

import org.junit.Test;
import static org.junit.Assert.*;

public class EmpresaTest {

    @Test
    public void testEmpresaGettersSetters() {
        Empresa empresa = new Empresa();
        empresa.setId(42L);
        empresa.setNomeFantasia("Teste");
        empresa.setRazaoSocial("Razao Social");
        empresa.setCnpj("12345678901234");

        assertEquals(Long.valueOf(42), empresa.getId());
        assertEquals("Teste", empresa.getNomeFantasia());
        assertEquals("Razao Social", empresa.getRazaoSocial());
        assertEquals("12345678901234", empresa.getCnpj());
    }

    @Test
    public void testEmpresaConstructor() {
        Empresa empresa = new Empresa("NF", "RS", "12345678901234");
        assertEquals("NF", empresa.getNomeFantasia());
        assertEquals("RS", empresa.getRazaoSocial());
        assertEquals("12345678901234", empresa.getCnpj());
    }
}