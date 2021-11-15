package com.lasa.data.model.view;

import com.lasa.data.model.entity.Lecturer;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LecturerViewModel implements Serializable {
    private Integer id;
    private String email;
    private String name;
    private String phone;
    private String meetingUrl;
    private Integer status;
    private Integer gender;
    private LocalDate birthday;
    private String address;
    private String avatarUrl;

    public LecturerViewModel(Lecturer lecturer) {
        this.id = lecturer.getId();
        this.email = lecturer.getEmail();
        this.name = lecturer.getName();
        this.phone = lecturer.getPhone();
        this.meetingUrl = lecturer.getMeetingUrl();
        this.status = lecturer.getStatus();
        this.gender = lecturer.getGender();
        this.birthday = lecturer.getBirthday();
        this.address = lecturer.getAddress();
        this.avatarUrl = lecturer.getAvatarUrl();
    }
}
