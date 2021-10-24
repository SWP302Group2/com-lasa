package com.lasa.data.model.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import com.lasa.data.model.entity.Admin;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AdminRequestModel {
    private Integer id;
    private String name;
    private String username;
    private String password;
    private String email;
    private String phone;
    private Boolean gender;
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss[.SSS][.SS][.S]")
    private LocalDateTime birthday;
    private String avatarUrl;

    public Admin toEntity() {
        return Admin.builder()
                .id(id)
                .name(name)
                .username(username)
                .password(password)
                .email(email)
                .phone(phone)
                .gender(gender)
                .birthday(birthday)
                .avatarUrl(avatarUrl)
                .build();
    }
}
