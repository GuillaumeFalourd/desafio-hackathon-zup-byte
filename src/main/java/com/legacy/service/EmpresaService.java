package com.legacy.service;

import com.legacy.dao.EmpresaDAO;
import com.legacy.model.Empresa;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public class EmpresaService {
    private EmpresaDAO empresaDAO;

    public void setEmpresaDAO(EmpresaDAO empresaDAO) {
        this.empresaDAO = empresaDAO;
    }

    @Transactional
    public void registrarEmpresa(Empresa empresa) {
        // Validação simples de CNPJ
        if (empresa.getCnpj() == null || empresa.getCnpj().length() != 14) {
            throw new IllegalArgumentException("CNPJ inválido");
        }
        
        List<Empresa> todas = empresaDAO.listarTodas();
        for (Empresa e : todas) {
            if (e.getCnpj().equals(empresa.getCnpj())) {
                throw new RuntimeException("CNPJ já cadastrado");
            }
        }
        
        empresaDAO.salvar(empresa);
    }

    @Transactional(readOnly = true)
    public Empresa buscarEmpresa(Long id) {
        return empresaDAO.buscarPorId(id);
    }

    @Transactional(readOnly = true)
    public List<Empresa> listarEmpresas() {
        return empresaDAO.listarTodas();
    }
}
