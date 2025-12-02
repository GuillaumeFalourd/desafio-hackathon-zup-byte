package com.legacy.exception;

import java.util.ArrayList;
import java.util.List;

public class ValidationException extends BusinessException {
    
    private List<String> erros;
    
    public ValidationException(String message) {
        super(message);
        this.erros = new ArrayList<>();
        this.erros.add(message);
    }
    
    public ValidationException(List<String> erros) {
        super("Erros de validação encontrados");
        this.erros = erros != null ? erros : new ArrayList<>();
    }
    
    public List<String> getErros() {
        return erros;
    }
    
    public void adicionarErro(String erro) {
        if (this.erros == null) {
            this.erros = new ArrayList<>();
        }
        this.erros.add(erro);
    }
    
    public boolean temErros() {
        return erros != null && !erros.isEmpty();
    }
}