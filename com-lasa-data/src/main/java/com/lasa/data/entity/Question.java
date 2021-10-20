/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lasa.data.entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import io.swagger.annotations.ApiModelProperty;
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
@Table(name = "question")
@Data
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_EMPTY)
@JsonIdentityInfo(generator= ObjectIdGenerators.IntSequenceGenerator.class, property="@id", scope = Question.class)
public class Question implements Serializable {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty(name = "id", hidden = true, required = false)
    private Integer id;


    @ManyToOne(targetEntity = BookingRequest.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "bookingid")
    private BookingRequest bookingRequest;

    @Column(name = "content")
    @ApiModelProperty(name = "content", example = "question1?", dataType = "String") 
    private String content;

    @Builder
    public Question(Integer id, BookingRequest bookingRequest, String content) {
        this.id = id;
        this.bookingRequest = bookingRequest;
        this.content = content;
    }
}
