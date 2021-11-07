package com.lasa.security.utils.exception;

public class ExceptionUtils {

    public static class TokenException extends RuntimeException {
        public TokenException(String message) {
            super(message);
        }
    }

    public static class EmailSenderException extends RuntimeException {
        public EmailSenderException(String message) {
            super(message);
        }
    }

    public static class UserAccountException extends RuntimeException {
        public  UserAccountException(String message) {
            super(message);
        }
    }

    public static class EmailDomainException extends Exception {
        public EmailDomainException(String message) {
            super(message);
        }
    }

    public static class UserAlreadyExistException extends Exception {
        public UserAlreadyExistException(String message) {
            super(message);
        }
    }

    public static class ArgumentException extends Exception {
        public ArgumentException(String message) {
            super(message);
        }
    }

    public static class DuplicatedException extends Exception {
        public DuplicatedException(String message) {
            super(message);
        }
    }

    public static class DeleteException extends Exception {
        public DeleteException(String message) {
            super(message);
        }
    }

    public static class UpdateException extends Exception {
        public UpdateException(String message) {
            super(message);
        }
    }
}
