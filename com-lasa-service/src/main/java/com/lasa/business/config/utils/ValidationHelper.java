package com.lasa.business.config.utils;

public class ValidationHelper {

    public static boolean emailValidation(String email) {
        if(email.endsWith("@fpt.edu.vn") || email.endsWith("@fe.edu.vn"))
            return true;
        return false;
    }
}
