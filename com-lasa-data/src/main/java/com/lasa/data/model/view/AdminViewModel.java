package com.lasa.data.model.view;

import com.lasa.data.model.entity.Admin;
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
public class AdminViewModel implements Serializable {
    private Integer id;
    private String name;
    private String username;
    private String password;
    private String email;
    private String phone;
    private Integer gender;
    private LocalDateTime birthday;
    private String avatarUrl;

    public AdminViewModel(Admin admin) {
        this.id = admin.getId();
        this.name = admin.getName();
        this.username = admin.getUsername();
        this.password = admin.getPassword();
        this.email = admin.getEmail();
        this.phone = admin.getPhone();
        this.gender = admin.getGender();
        this.birthday = admin.getBirthday();
        this.avatarUrl = admin.getAvatarUrl();
    }

}
