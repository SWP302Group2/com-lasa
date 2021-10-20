package com.lasa.data.dto;

import com.lasa.data.entity.BookingRequest;
import com.lasa.data.entity.FavoriteLecturer;
import com.lasa.data.entity.Student;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.stream.Collectors;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class StudentDTO implements Serializable {
    private Integer id;
    private String email;
    private String mssv;
    private String majorId;
    private String name;
    private String phone;
    private Integer status;
    private Boolean gender;
    private LocalDate birthday;
    private String address;
    private String avatarUrl;

    public StudentDTO(Student student) {
        this.id = student.getId();
        this.email = student.getEmail();
        this.mssv = student.getMssv();
        this.majorId = student.getMajorId();
        this.name = student.getName();
        this.phone = student.getPhone();
        this.status = student.getStatus();
        this.gender = student.getGender();
        this.birthday = student.getBirthday();
        this.address = student.getAddress();
        this.avatarUrl = student.getAvatarUrl();
    }
}
