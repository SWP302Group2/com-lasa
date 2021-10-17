/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lasa.data.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
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
    
    @Column(name = "bookingid")
    private Integer bookingId;

    @Column(name = "content")
    private String content;

    @Builder
    public Question(Integer id, Integer bookingId, String content) {
        this.id = id;
        this.bookingId = bookingId;
        this.content = content;
    }

    
}
