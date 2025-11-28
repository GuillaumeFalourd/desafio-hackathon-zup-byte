package com.legacy.service;

import com.legacy.dao.EmpresaDAO;
import com.legacy.model.Empresa;
import java.util.List;

public class EmpresaService {
    private EmpresaDAO empresaDAO;

    public void setEmpresaDAO(EmpresaDAO empresaDAO) {
        this.empresaDAO = empresaDAO;
    }

    public void registrarEmpresa(Empresa empresa) {
        // Validação simples de CNPJ
        if (empresa.getCnpj() == null || empresa.getCnpj().length() != 14) {
            throw new IllegalArgumentException("CNPJ inválido");
        }
        empresaDAO.salvar(empresa);
    }

    public Empresa buscarEmpresa(Long id) {
        return empresaDAO.buscarPorId(id);
    }

    public List<Empresa> listarEmpresas() {
        return empresaDAO.listarTodas();
    }
}
