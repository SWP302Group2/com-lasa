/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lasa.data.entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
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
public class Question implements Serializable {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;


    @ManyToOne(targetEntity = BookingRequest.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "bookingid")
    private BookingRequest bookingRequest;

    @Column(name = "content")
    private String content;

    @Builder
    public Question(Integer id, BookingRequest bookingRequest, String content) {
        this.id = id;
        this.bookingRequest = bookingRequest;
        this.content = content;
    }
}
