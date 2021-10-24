package com.lasa.data.model.view;

import com.lasa.data.model.entity.FavoriteLecturer;
import com.lasa.data.model.entity.Lecturer;
import com.lasa.data.model.entity.Student;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FavoriteLecturerViewModel implements Serializable {
    private Student student;
    private Lecturer lecturer;

    public FavoriteLecturerViewModel(FavoriteLecturer favoriteLecturer) {
        this.student = favoriteLecturer.getStudent();
        this.lecturer = favoriteLecturer.getLecturer();
    }
}
