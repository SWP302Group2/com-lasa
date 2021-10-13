package com.lasa.data.entity.utils;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.time.LocalDate;
import java.time.LocalDateTime;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
public interface AdminSimple {
    Integer getId();
    String getUsername();
    String getName();
    String getEmail();
    String getPhone();
    Boolean getGender();
    LocalDateTime getBirthday();
    String getAvatarUrl();

}
