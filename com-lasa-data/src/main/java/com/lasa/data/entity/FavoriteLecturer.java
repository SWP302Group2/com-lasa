/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lasa.data.entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.lasa.data.entity.key.FavoriteLecturerKey;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

/**
 *
 * @author hai
 */
@Entity
@Table(name = "favoritelecturer")
@Data
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_EMPTY)
@JsonIdentityInfo(generator=ObjectIdGenerators.IntSequenceGenerator.class, property="@id", scope = FavoriteLecturer.class)
public class FavoriteLecturer implements Serializable{
    
    @JsonIgnore
    @EmbeddedId
    private FavoriteLecturerKey id = new FavoriteLecturerKey();
    
    @ManyToOne(targetEntity = Student.class, fetch = FetchType.LAZY)
    @MapsId("studentId")
    @JoinColumn(name = "studentid")
    private Student student;

    @ManyToOne(targetEntity = Lecturer.class, fetch = FetchType.LAZY)
    @MapsId("lecturerId")
    @JoinColumn(name = "lecturerid")
    private Lecturer lecturer;

    @Builder
    public FavoriteLecturer(Student student, Lecturer lecturer) {
        this.student = student;
        this.lecturer = lecturer;
    }
}
