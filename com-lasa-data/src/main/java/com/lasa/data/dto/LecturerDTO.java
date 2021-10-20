package com.lasa.data.dto;

import com.lasa.data.entity.FavoriteLecturer;
import com.lasa.data.entity.Lecturer;
import com.lasa.data.entity.LecturerTopicDetail;
import com.lasa.data.entity.Slot;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Collection;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LecturerDTO implements Serializable {
    private Integer id;
    private String email;
    private String name;
    private String phone;
    private String meetingUrl;
    private Integer status;
    private Boolean gender;
    private LocalDate birthday;
    private String address;
    private String avatarUrl;

    public LecturerDTO(Lecturer lecturer) {
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
