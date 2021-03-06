/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lasa.data.model.entity.key;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

/**
 *
 * @author hai
 */
@Embeddable
@NoArgsConstructor
@Data
public class FavoriteLecturerKey implements Serializable {
    
    @Column(name = "studentid")
    private Integer studentId;
    
    @Column(name = "lecturerid")
    private Integer lecturerId;

    @Builder
    public FavoriteLecturerKey(Integer studentId, Integer lecturerId) {
        this.studentId = studentId;
        this.lecturerId = lecturerId;
    }
    
}
