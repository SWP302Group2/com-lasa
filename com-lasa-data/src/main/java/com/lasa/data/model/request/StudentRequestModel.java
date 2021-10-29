package com.lasa.data.model.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.lasa.data.model.entity.Student;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class StudentRequestModel {
    private Integer id;
    private String mssv;
    private String majorId;
    private String name;
    private String phone;
    private Integer status;
    private Boolean gender;
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss[.SSS][.SS][.S]")
    private LocalDateTime birthday;
    private String address;
    private String avatarUrl;

    public Student toEntity() {
        return Student.builder()
                .id(id)
                .mssv(mssv)
                .name(name)
                .phone(phone)
                .status(status)
                .gender(gender)
                .birthday(birthday)
                .avatarUrl(avatarUrl)
                .address(address)
                .majorId(majorId)
                .build();
    }
}
