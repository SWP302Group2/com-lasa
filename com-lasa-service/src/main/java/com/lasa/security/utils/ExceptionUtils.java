package com.lasa.security.utils;

public class ExceptionUtils {
    public static class TokenInvalidException extends RuntimeException{
        public TokenInvalidException(String message) {
            super(message);
        }
    }

    public static class UserAccountException extends RuntimeException{
        public UserAccountException(String message) {
            super(message);
        }
    }
}
