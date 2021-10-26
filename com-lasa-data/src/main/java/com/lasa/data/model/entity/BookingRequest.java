/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lasa.data.model.entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
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
    @ApiModelProperty(name = "id", hidden = true, required = false)
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer id;
      
    @Column(name = "studentid")
    @ApiModelProperty(name = "studentid", hidden = true)
    private Integer studentId;
      
    @Column(name = "status")
    @ApiModelProperty(name = "status", example = "1", dataType = "Integer", position = 1)
    private Integer status;
    
    @OneToMany(targetEntity = Question.class, mappedBy = "bookingRequest", cascade = {CascadeType.ALL})
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @ApiModelProperty(name = "questions", dataType = "List", position = 4)
    private List<Question> questions;

    @Column(name = "topicid")
    @ApiModelProperty(name = "topicid", example = "2", dataType = "Integer", position = 2)
    private Integer topicId;
    
    @Column(name = "slotid")
    @ApiModelProperty(name = "slotid", example = "5", dataType = "Integer", position = 3) 
    private Integer slotId;

    @Column(name = "title")
    @ApiModelProperty(name = "title", example = "ok", dataType = "String", position = 5) 
    private String title;

    @Column(name = "createtime")
    private LocalDateTime createTime;

    @Column(name = "rating")
    private Integer rating;

    @Builder
    public BookingRequest(Integer id, Integer studentId, Integer status, List<Question> questions, Integer topicId, Integer slotId, String title, LocalDateTime createTime, Integer rating) {
        this.id = id;
        this.studentId = studentId;
        this.status = status;
        this.questions = questions;
        this.topicId = topicId;
        this.slotId = slotId;
        this.title = title;
        this.createTime = createTime;
        this.rating = rating;
    }
}
