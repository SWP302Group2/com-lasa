package com.lasa.data.model.request;

import com.lasa.data.model.entity.FavoriteLecturer;
import com.lasa.data.model.entity.Lecturer;
import com.lasa.data.model.entity.Student;
import com.lasa.data.validator.ValidLecturerId;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Min;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FavoriteLecturerRequestModel {
    @Min(0)
    @ValidLecturerId
    private Integer lecturerId;
    @Min(0)
    private Integer studentId;

    public FavoriteLecturer toEntity() {
        return FavoriteLecturer.builder()
                .lecturer(Lecturer.builder()
                        .id(this.lecturerId)
                        .build())
                .student(Student.builder()
                        .id(this.studentId)
                        .build())
                .build();
    }

    public FavoriteLecturer toEntity(Integer studentId, Integer lecturerId) {
        this.lecturerId = lecturerId;
        this.studentId = studentId;
        return toEntity();
    }
}
