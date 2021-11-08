package com.lasa.data.model.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.lasa.data.model.entity.Student;
import com.lasa.data.validator.ValidLecturerIds;
import com.lasa.data.validator.ValidMajorId;
import com.lasa.data.validator.ValidOneOf;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class StudentRequestModel {
    private Integer id;
    private String mssv;
    @ValidMajorId(message = "MAJOR_ID_NOT_AVAILABLE_FOR_UPDATE")
    private String majorId;
    private String name;
    private String phone;
    @ValidOneOf(value = {-1,0,1})
    private Integer status;
    private Boolean gender;
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss[.SSS][.SS][.S]")
    private LocalDateTime birthday;
    private String address;
    private String avatarUrl;
    @ValidLecturerIds(message = "LECTURER_NOT_AVAILABLE_FOR_UPDATE")
    private List<Integer> lecturers;

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
