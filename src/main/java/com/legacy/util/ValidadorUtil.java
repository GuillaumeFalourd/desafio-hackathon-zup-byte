package com.legacy.util;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Pattern;

public class ValidadorUtil {
    
    private static final Log logger = LogFactory.getLog(ValidadorUtil.class);
    
    private static final String CNPJ_PATTERN = "\\d{14}";
    private static final String EMAIL_PATTERN = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";
    
    /**
     * @param cnpj CNPJ para validar
     * @return true se válido
     */
    public static boolean validarCnpj(String cnpj) {
        if (cnpj == null) {
            return false;
        }
        
        // Remove caracteres especiais
        String cnpjLimpo = cnpj.replaceAll("[^0-9]", "");
        
        // Verifica se tem 14 dígitos
        if (!Pattern.matches(CNPJ_PATTERN, cnpjLimpo)) {
            logger.warn("CNPJ inválido: " + cnpj);
            return false;
        }
        
        // Verifica se não são todos os dígitos iguais
        if (cnpjLimpo.matches("(\\d)\\1{13}")) {
            return false;
        }
        
        return true;
    }
    
    /**
     * Validação de email usando regex simples
     */
    public static boolean validarEmail(String email) {
        if (email == null || email.trim().isEmpty()) {
            return false;
        }
        return Pattern.matches(EMAIL_PATTERN, email);
    }
    
    /**
     * Valida se string não é nula ou vazia
     */
    public static boolean validarCampoObrigatorio(String campo) {
        return campo != null && !campo.trim().isEmpty();
    }
    
    /**
     * Formata CNPJ para exibição
     */
    public static String formatarCnpj(String cnpj) {
        if (cnpj == null) {
            return "";
        }
        
        String cnpjLimpo = cnpj.replaceAll("[^0-9]", "");
        
        if (cnpjLimpo.length() != 14) {
            return cnpj; // Retorna original se inválido
        }
        
        StringBuilder sb = new StringBuilder();
        sb.append(cnpjLimpo.substring(0, 2));
        sb.append(".");
        sb.append(cnpjLimpo.substring(2, 5));
        sb.append(".");
        sb.append(cnpjLimpo.substring(5, 8));
        sb.append("/");
        sb.append(cnpjLimpo.substring(8, 12));
        sb.append("-");
        sb.append(cnpjLimpo.substring(12, 14));
        
        return sb.toString();
    }
    
    public static void logOperacao(String operacao, String detalhes) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String timestamp = sdf.format(new Date());
        logger.info("[" + timestamp + "] " + operacao + ": " + detalhes);
    }
    
    public static boolean validarTamanhoNomeFantasia(String nome) {
        if (nome == null) return false;
        return nome.trim().length() >= 2 && nome.trim().length() <= 100;
    }
    
    public static boolean validarTamanhoRazaoSocial(String razao) {
        if (razao == null) return false;
        return razao.trim().length() >= 2 && razao.trim().length() <= 150;
    }
}