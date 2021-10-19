package com.lasa.data.entity.utils.projection;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.time.LocalDateTime;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
public interface SimpleAdmin {
    Integer getId();
    String getUsername();
    String getName();
    String getEmail();
    String getPhone();
    Boolean getGender();
    LocalDateTime getBirthday();
    String getAvatarUrl();

}
