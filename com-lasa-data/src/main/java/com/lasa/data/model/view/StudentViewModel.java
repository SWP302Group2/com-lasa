package com.lasa.data.model.view;

import com.lasa.data.model.entity.Student;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class StudentViewModel implements Serializable {
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

    public StudentViewModel(Student student) {
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
