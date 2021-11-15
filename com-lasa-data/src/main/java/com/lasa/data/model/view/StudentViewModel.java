package com.lasa.data.model.view;

import com.lasa.data.model.entity.Student;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDateTime;

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
    private Integer gender;
    private LocalDateTime birthday;
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
