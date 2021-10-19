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
import java.time.LocalDate;
import java.util.Collection;

/**
 *
 * @author hai
 */
@Entity
@NamedEntityGraph(
        name = "graph.Student.bookingRequests",
        attributeNodes = @NamedAttributeNode("bookingRequests")
        )
@Table(name = "student")
@Data
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_EMPTY)
@JsonIdentityInfo(generator=ObjectIdGenerators.IntSequenceGenerator.class, property="@id", scope = Student.class)
public class Student implements Serializable {
    
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    @Column(name = "email")
    private String email;
    
    @Column(name = "mssv")
    private String mssv;
    
    @Column(name = "majorid")
    private String majorId;
    
    @Column(name = "name")
    private String name;
    
    @Column(name = "phone")
    private String phone;
    
    @OneToMany(targetEntity = BookingRequest.class, mappedBy = "studentId")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Collection<BookingRequest> bookingRequests;
    
    @OneToMany(targetEntity = FavoriteLecturer.class,mappedBy = "student")
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Collection<FavoriteLecturer> favoriteLecturer;
    
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
    public Student(Integer id, String email, String mssv, String majorId, String name, String phone, Collection<BookingRequest> bookingRequests, Collection<FavoriteLecturer> favoriteLecturer, Integer status, Boolean gender, LocalDate birthday, String address, String avatarUrl) {
        this.id = id;
        this.email = email;
        this.mssv = mssv;
        this.majorId = majorId;
        this.name = name;
        this.phone = phone;
        this.bookingRequests = bookingRequests;
        this.favoriteLecturer = favoriteLecturer;
        this.status = status;
        this.gender = gender;
        this.birthday = birthday;
        this.address = address;
        this.avatarUrl = avatarUrl;
    }
}
