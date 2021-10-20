package com.lasa.data.dto;

import com.lasa.data.entity.Admin;
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
public class AdminDTO implements Serializable {
    private Integer id;
    private String name;
    private String username;
    private String password;
    private String email;
    private String phone;
    private Boolean gender;
    private LocalDateTime birthday;
    private String avatarUrl;

    public AdminDTO(Admin admin) {
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
