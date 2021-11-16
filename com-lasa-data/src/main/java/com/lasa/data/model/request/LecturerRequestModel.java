package com.lasa.data.model.request;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.lasa.data.model.entity.Lecturer;
import com.lasa.data.validator.ValidOneOf;
import com.lasa.data.validator.ValidTopicIds;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LecturerRequestModel {
    private Integer id;
    private String name;
    private String phone;
    private String meetingUrl;
    @ValidOneOf(value = {-2,-1,0,1})
    private Integer status;
    @ValidOneOf(value = {-1,0,1})
    private Integer  gender;
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss[.SSS][.SS][.S]")
    private LocalDate birthday;
    private String address;
    private String avatarUrl;
    @ValidTopicIds(message = "TOPIC_NOT_AVAILABLE_FOR_UPDATE")
    private List<Integer> topics;

    public Lecturer toEntity() {
        return Lecturer.builder()
                .id(id)
                .name(name)
                .phone(phone)
                .meetingUrl(meetingUrl)
                .status(status)
                .gender(gender)
                .birthday(birthday)
                .address(address)
                .avatarUrl(avatarUrl)
                .build();
    }
}
