package com.lasa.data.dto;

import com.lasa.data.entity.FavoriteLecturer;
import com.lasa.data.entity.Lecturer;
import com.lasa.data.entity.Student;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FavoriteLecturerDTO {
    private Student student;
    private Lecturer lecturer;

    public FavoriteLecturerDTO(FavoriteLecturer favoriteLecturer) {
        this.student = favoriteLecturer.getStudent();
        this.lecturer = favoriteLecturer.getLecturer();
    }
}
