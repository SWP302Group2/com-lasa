/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lasa.data.entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 *
 * @author hai
 */
@Entity
@Table(name = "bookingrequest")
@Data
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_EMPTY)
@JsonIdentityInfo(generator= ObjectIdGenerators.IntSequenceGenerator.class, property="@id")
public class BookingRequest implements Serializable {
    
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer id;
      
    @Column(name = "studentid")
    private Integer studentId;
     
    @Column(name = "status")
    private Integer status;
    
    @OneToMany(targetEntity = Question.class, mappedBy = "bookingRequest", cascade = {CascadeType.ALL})
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private List<Question> questions;

    @Column(name = "topicid")
    private Integer topicId;
    
    @Column(name = "slotid")
    private Integer slotId;

    @Column(name = "title")
    private String title;

    @Builder
    public BookingRequest(Integer id, Integer studentId, Integer status, List<Question> questions, Integer topicId, Integer slotId, String title) {
        this.id = id;
        this.studentId = studentId;
        this.status = status;
        this.questions = questions;
        this.topicId = topicId;
        this.slotId = slotId;
        this.title = title;
    }
}
