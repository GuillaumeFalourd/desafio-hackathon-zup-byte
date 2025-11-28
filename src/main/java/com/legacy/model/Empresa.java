package com.legacy.model;

import javax.persistence.*;
import java.util.Objects;

/**
 * Representa uma empresa com dados essenciais, incluindo CNPJ como String
 * para garantir flexibilidade e evitar problemas de precisão/validação.
 */
@Entity
@Table(name = "empresa")
public class Empresa {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nome_fantasia", nullable = false)
    private String nomeFantasia;

    @Column(name = "razao_social", nullable = false)
    private String razaoSocial;

    /**
     * CNPJ da empresa pode conter caracteres alfanuméricos.
     * Usar String é considerado boa prática para evitar perda de dados e facilitar validação/máscaras.
     */
    @Column(name = "cnpj", nullable = false, length = 18, unique = true)
    private String cnpj;

    public Empresa() {}

    public Empresa(String nomeFantasia, String razaoSocial, String cnpj) {
        this.nomeFantasia = nomeFantasia;
        this.razaoSocial = razaoSocial;
        this.cnpj = cnpj;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNomeFantasia() {
        return nomeFantasia;
    }

    public void setNomeFantasia(String nomeFantasia) {
        this.nomeFantasia = nomeFantasia;
    }

    public String getRazaoSocial() {
        return razaoSocial;
    }

    public void setRazaoSocial(String razaoSocial) {
        this.razaoSocial = razaoSocial;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Empresa)) return false;
        Empresa empresa = (Empresa) o;
        return Objects.equals(id, empresa.id) &&
               Objects.equals(cnpj, empresa.cnpj);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, cnpj);
    }

    @Override
    public String toString() {
        return "Empresa{" +
                "id=" + id +
                ", nomeFantasia='" + nomeFantasia + '\'' +
                ", razaoSocial='" + razaoSocial + '\'' +
                ", cnpj='" + cnpj + '\'' +
                '}';
    }
}