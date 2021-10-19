package com.lasa.security.utils.exception;

public class ExceptionUtils {

    public static class TokenException extends RuntimeException{
        public TokenException(String message) {
            super(message);
        }
    }

    public static class UserAccountException extends RuntimeException{
        public  UserAccountException(String message) {
            super(message);
        }
    }

    public static class EmailDomainException extends Exception{
        public EmailDomainException(String message) {
            super(message);
        }
    }

    public static class UserAlreadyExistException extends Exception{
        public UserAlreadyExistException(String message) {
            super(message);
        }
    }

    public static class ArgumentException extends Exception{
        public ArgumentException(String message) {
            super(message);
        }
    }
}
