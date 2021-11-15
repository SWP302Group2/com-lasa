/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lasa.data.model.entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Collection;

/**
 *
 * @author hai
 */
@Entity
@Table(name = "lecturer")
@Data
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_EMPTY)
@JsonIdentityInfo(generator=ObjectIdGenerators.IntSequenceGenerator.class, property="@id", scope = Lecturer.class)
public class Lecturer implements Serializable {
    
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    @Column(name = "email")
    private String email;

    @Column(name = "name")
    private String name;
    
    @Column(name = "phone")
    private String phone;

    @Column(name = "meetingurl")
    private String meetingUrl;
    
    @OneToMany(targetEntity = FavoriteLecturer.class,mappedBy = "lecturer", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Collection<FavoriteLecturer> students;
    
    @OneToMany(targetEntity = LecturerTopicDetail.class,mappedBy = "lecturer", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Collection<LecturerTopicDetail> topics;
    
    @OneToMany(targetEntity = Slot.class, mappedBy = "lecturerId", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Collection<Slot> slots;

    @Column(name = "status")
    private Integer status;
    
    @Column(name = "gender")
    private Integer gender;
    
    @Column(name = "birthday")
    private LocalDate birthday;
    
    @Column(name = "address")
    private String address;
    
    @Column(name = "avatarurl")
    private String avatarUrl;

    @Builder
    public Lecturer(Integer id, String email, String name, String phone, String meetingUrl, Collection<FavoriteLecturer> students, Collection<LecturerTopicDetail> topics, Collection<Slot> slots, Integer status, Integer gender, LocalDate birthday, String address, String avatarUrl) {
        this.id = id;
        this.email = email;
        this.name = name;
        this.phone = phone;
        this.meetingUrl = meetingUrl;
        this.students = students;
        this.topics = topics;
        this.slots = slots;
        this.status = status;
        this.gender = gender;
        this.birthday = birthday;
        this.address = address;
        this.avatarUrl = avatarUrl;
    }
}
