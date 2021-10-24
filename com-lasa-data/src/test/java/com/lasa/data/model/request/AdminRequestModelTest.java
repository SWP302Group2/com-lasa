package com.lasa.data.model.request;

import com.lasa.data.model.entity.Admin;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;

@SpringBootTest
public class AdminRequestModelTest {

    @Test
    public void toEntityTestGivenRightArgumentReturnWells() {
        AdminRequestModel model = new AdminRequestModel();
        String name = "Trung";
        String email = "admin@fpt.edu.vn";
        LocalDateTime birthDay = LocalDateTime.now();
        String password = "123333";
        String phone = "0934756722";
        Boolean gender = true;
        Integer status = 1;
        String avatarUrl = "https://avatar.com";
        Integer id = 1;
        String username = "admin";

        model.setId(id);
        model.setName(name);
        model.setEmail(email);
        model.setBirthday(LocalDateTime.now());
        model.setPassword(password);
        model.setGender(gender);
        model.setPhone(phone);
        model.setUsername(username);

        Admin admin = model.toEntity();

        Assertions.assertEquals(admin.getName(), name);
        Assertions.assertEquals(admin.getId(), id);
        Assertions.assertEquals(admin.getPhone(), phone);
        Assertions.assertEquals(admin.getGender(), gender);
        Assertions.assertEquals(admin.getEmail(), email);
        Assertions.assertEquals(admin.getPassword(), password);
        Assertions.assertEquals(admin.getBirthday(), birthDay);
        Assertions.assertEquals(admin.getUsername(), username);
    }
}
