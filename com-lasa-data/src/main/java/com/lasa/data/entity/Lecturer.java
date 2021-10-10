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
import java.util.Set;
import javax.persistence.*;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import net.bytebuddy.implementation.bind.annotation.Default;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

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
    
    @OneToMany(targetEntity = FavoriteLecturer.class,mappedBy = "lecturer")
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Set<FavoriteLecturer> students;
    
    @OneToMany(targetEntity = LecturerTopicDetail.class,mappedBy = "lecturer")
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Set<LecturerTopicDetail> topics;
    
    @OneToMany(targetEntity = Slot.class, mappedBy = "lecturerId")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Set<Slot> slots;

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
    public Lecturer(Integer id, String email, String name, String phone, String meetingUrl, Set<FavoriteLecturer> students, Set<LecturerTopicDetail> topics, Set<Slot> slots, Integer status, Boolean gender, LocalDate birthday, String address, String avatarUrl) {
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

    public Lecturer(Integer id, String email, String name) {
        this.id = id;
        this.email = email;
        this.name = name;
    }
    public Lecturer(Integer id, String email, String name, Slot slots) {
        System.out.println(id);
        this.id = id;
        this.email = email;
        this.name = name;
        System.out.println(slots);
    }

}
