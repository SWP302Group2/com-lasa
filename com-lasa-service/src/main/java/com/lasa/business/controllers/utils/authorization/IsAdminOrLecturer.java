package com.lasa.business.controllers.utils.authorization;

import org.springframework.security.access.annotation.Secured;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Secured({"ROLE_ADMIN", "ROLE_LECTURER"})
public @interface IsAdminOrLecturer {
}
