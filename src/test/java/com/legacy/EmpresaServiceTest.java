package com.legacy.service;

import com.legacy.dao.EmpresaDAO;
import com.legacy.model.Empresa;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.junit.Assert.*;

public class EmpresaServiceTest {

    private EmpresaService empresaService;
    private EmpresaDAO empresaDAO;

    @Before
    public void setUp() {
        empresaDAO = mock(EmpresaDAO.class);
        empresaService = new EmpresaService();
        empresaService.setEmpresaDAO(empresaDAO);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testRegistrarEmpresaCnpjInvalido() {
        Empresa empresa = new Empresa("Test", "Razao", "123"); // CNPJ inválido
        empresaService.registrarEmpresa(empresa);
    }

    @Test
    public void testRegistrarEmpresaCnpjValido() {
        Empresa empresa = new Empresa("Test", "Razao", "12345678901234");
        empresaService.registrarEmpresa(empresa);
        verify(empresaDAO, times(1)).salvar(empresa);
    }

    @Test
    public void testBuscarEmpresa() {
        Empresa empresa = new Empresa();
        when(empresaDAO.buscarPorId(1L)).thenReturn(empresa);

        Empresa resultado = empresaService.buscarEmpresa(1L);
        assertEquals(empresa, resultado);
    }

    @Test
    public void testListarEmpresas() {
        List<Empresa> list = Arrays.asList(new Empresa());
        when(empresaDAO.listarTodas()).thenReturn(list);

        List<Empresa> resultado = empresaService.listarEmpresas();
        assertEquals(list, resultado);
    }
}