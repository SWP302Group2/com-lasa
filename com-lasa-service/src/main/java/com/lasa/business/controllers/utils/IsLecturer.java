package com.lasa.business.controllers.utils;

import org.springframework.security.access.prepost.PreAuthorize;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@PreAuthorize("hasRole('ROLE_' + T(com.lasa.security.utils.permission.ApplicationUserRole).LECTURER.name())")
public @interface IsLecturer {
}
