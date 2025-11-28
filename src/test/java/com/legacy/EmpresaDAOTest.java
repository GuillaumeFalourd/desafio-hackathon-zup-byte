package com.legacy.dao;

import com.legacy.model.Empresa;
import org.hibernate.SessionFactory;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.orm.hibernate4.HibernateTemplate;

import java.util.Collections;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class EmpresaDAOTest {

    private EmpresaDAO empresaDAO;
    private HibernateTemplate hibernateTemplate;

    @Before
    public void setUp() {
        empresaDAO = new EmpresaDAO();
        SessionFactory sessionFactory = Mockito.mock(SessionFactory.class);
        empresaDAO.setSessionFactory(sessionFactory);

        hibernateTemplate = mock(HibernateTemplate.class);
        empresaDAO.setHibernateTemplate(hibernateTemplate);
    }

    @Test
    public void testSalvar() {
        Empresa empresa = new Empresa();
        empresaDAO.salvar(empresa);

        verify(hibernateTemplate, times(1)).save(empresa);
    }

    @Test
    public void testBuscarPorId() {
        Empresa empresa = new Empresa();
        when(hibernateTemplate.get(Empresa.class, 1L)).thenReturn(empresa);

        Empresa resultado = empresaDAO.buscarPorId(1L);
        assertEquals(empresa, resultado);
    }

    @Test
    public void testListarTodas() {
        List<Empresa> empresas = Collections.singletonList(new Empresa());
        when(hibernateTemplate.find("from Empresa")).thenReturn(empresas);

        List<Empresa> resultado = empresaDAO.listarTodas();
        assertEquals(empresas, resultado);
    }
}