package com.legacy.dao;

import com.legacy.model.Empresa;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.orm.hibernate4.support.HibernateDaoSupport;

import java.util.List;

public class EmpresaDAO extends HibernateDaoSupport {

    public void salvar(Empresa empresa) {
        getHibernateTemplate().save(empresa);
    }

    public Empresa buscarPorId(Long id) {
        return getHibernateTemplate().get(Empresa.class, id);
    }

    public List<Empresa> listarTodas() {
        return (List<Empresa>) getHibernateTemplate().find("from Empresa");
    }
    
    public void atualizar(Empresa empresa) {
        getHibernateTemplate().update(empresa);
    }
    
    public void remover(Long id) {
        Empresa empresa = buscarPorId(id);
        if (empresa != null) {
            getHibernateTemplate().delete(empresa);
        }
    }
}
