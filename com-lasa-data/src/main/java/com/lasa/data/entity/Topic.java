/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lasa.data.entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 *
 * @author hai
 */
@Entity
@Table(name = "topic")
@Data
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_EMPTY)
@JsonIdentityInfo(generator = ObjectIdGenerators.IntSequenceGenerator.class, property = "@id", scope = Topic.class)
public class Topic implements Serializable {
    
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    @Column(name = "name")   
    private String name;
    
    @OneToMany(targetEntity = LecturerTopicDetail.class,mappedBy = "topic")
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Collection<LecturerTopicDetail> lecturers;
    
    @Column(name = "majorid") 
    private String majorId;
    
    @OneToMany(targetEntity = BookingRequest.class, mappedBy = "topicId")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Collection<BookingRequest> bookingRequests;
    
    @OneToMany(targetEntity = SlotTopicDetail.class,mappedBy = "topic")
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Collection<SlotTopicDetail> slots;

    @Column(name = "courseid")
    private String courseId;
    
    @Column(name = "status")
    private Integer status;
    
    @Column(name = "description")
    private String description;

    @Builder
    public Topic(Integer id, String name, Collection<LecturerTopicDetail> lecturers, String majorId, Collection<BookingRequest> bookingRequests, Collection<SlotTopicDetail> slots, String courseId, Integer status, String description) {
        this.id = id;
        this.name = name;
        this.lecturers = lecturers;
        this.majorId = majorId;
        this.bookingRequests = bookingRequests;
        this.slots = slots;
        this.courseId = courseId;
        this.status = status;
        this.description = description;
    }

}
