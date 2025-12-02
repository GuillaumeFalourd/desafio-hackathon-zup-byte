package com.legacy.util;

public class StringUtil {
    
    public static boolean isEmpty(String str) {
        return str == null || str.length() == 0;
    }
    
    public static boolean isBlank(String str) {
        return str == null || str.trim().length() == 0;
    }
    
    public static String formatarMensagem(String template, String valor) {
        return template.replace("{0}", valor != null ? valor : "");
    }
}