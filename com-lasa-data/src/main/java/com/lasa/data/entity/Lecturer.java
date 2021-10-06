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
import java.time.LocalDate;
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
@Table(name = "lecturer")
@Data
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_EMPTY)
@JsonIdentityInfo(generator=ObjectIdGenerators.IntSequenceGenerator.class, property="@id", scope = Lecturer.class)
public class Lecturer implements Serializable {
    
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    
    @Column(name = "email")
    private String email;
    
    @Column(name = "name")
    private String name;
    
    @Column(name = "phone")
    private String phone;
    
    @Column(name = "meetingurl")
    private String meetingUrl;
    
    @OneToMany(targetEntity = FavoriteLecturer.class,mappedBy = "lecturer")
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Collection<FavoriteLecturer> students;
    
    @OneToMany(targetEntity = LecturerTopicDetail.class,mappedBy = "lecturer")
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Collection<LecturerTopicDetail> topics;
    
    @OneToMany(targetEntity = Slot.class, mappedBy = "lecturerId")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Collection<Slot> slots;

    @Column(name = "status")
    private Integer status;
    
    @Column(name = "gender")
    private Boolean gender;
    
    @Column(name = "birthday")
    private LocalDate birthday;
    
    @Column(name = "address")
    private String address;
    
    @Column(name = "avatarurl")
    private String avatarUrl;

    @Builder
    public Lecturer(int id, String email, String name, String phone, String meetingUrl, Collection<FavoriteLecturer> students, Collection<LecturerTopicDetail> topics, Collection<Slot> slots, Integer status, Boolean gender, LocalDate birthday, String address, String avatarUrl) {
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
