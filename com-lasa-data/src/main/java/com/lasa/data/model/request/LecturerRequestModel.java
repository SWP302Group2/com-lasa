package com.lasa.data.model.request;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.lasa.data.model.entity.Lecturer;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.Collection;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LecturerRequestModel {
    private Integer id;
    private String name;
    private String phone;
    private String meetingUrl;
    private Integer status;
    private Boolean gender;
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss[.SSS][.SS][.S]")
    private LocalDate birthday;
    private String address;
    private String avatarUrl;
    private Collection<Integer> topics;

    public Lecturer toEntity() {
        return Lecturer.builder()
                .id(id)
                .name(name)
                .phone(phone)
                .meetingUrl(meetingUrl)
                .status(status)
                .gender(gender)
                .birthday(birthday)
                .address(address)
                .avatarUrl(avatarUrl)
                .build();
    }
}
