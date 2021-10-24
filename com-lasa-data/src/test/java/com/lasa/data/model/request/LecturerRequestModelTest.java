/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lasa.data.model.request;

import com.lasa.data.model.entity.Lecturer;
import java.time.LocalDate;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;
import org.springframework.boot.test.context.SpringBootTest;

/**
 *
 * @author ASUS
 */
@SpringBootTest
public class LecturerRequestModelTest {
    @Test
    public void toEntityTestGivenRightArgumentReturnWells() {
        LecturerRequestModel model = new LecturerRequestModel();
        Integer lecturerId = 1;
        String lecturerName = "Vu Ngoc Bao";
        String phone = "0915215326";
        String meetingUrl = "https://meet.google.com/ryu-stnz-rww";
        Integer status = 1;
        Boolean gender = true;
        LocalDate birthDay = LocalDate.now();
        String address = "Binh Thuan";
        String avatarUrl = "https://avatar.com";

        model.setId(lecturerId);
        model.setName(lecturerName);
        model.setPhone(phone);
        model.setMeetingUrl(meetingUrl);
        model.setStatus(status);
        model.setGender(gender); 
        model.setBirthday(LocalDate.now());
        model.setAddress(address);
        model.setAvatarUrl(avatarUrl);

        Lecturer lecturer = model.toEntity();

        Assertions.assertEquals(lecturer.getId(), lecturerId);
        Assertions.assertEquals(lecturer.getName(), lecturerName);
        Assertions.assertEquals(lecturer.getPhone(), phone);
        Assertions.assertEquals(lecturer.getMeetingUrl(), meetingUrl);
        Assertions.assertEquals(lecturer.getStatus(), status);
        Assertions.assertEquals(lecturer.getGender(), gender);
        Assertions.assertEquals(lecturer.getBirthday(), birthDay);
        Assertions.assertEquals(lecturer.getAddress(), address);
        Assertions.assertEquals(lecturer.getAvatarUrl(), avatarUrl);
    }
    
}
