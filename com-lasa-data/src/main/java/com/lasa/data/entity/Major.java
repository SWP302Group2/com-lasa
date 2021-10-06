/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lasa.data.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Column;
import javax.persistence.Entity;
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
@Table(name = "major")
@Data
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class Major implements Serializable {
    
    @Id
    @Column(name = "id")
    private String id;
    
    @Column(name = "name")
    private String name;
    
    @Column(name = "description")
    private String description;

    @OneToMany(targetEntity = Student.class, mappedBy = "majorId")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Collection<Student> students;
    
    @OneToMany(targetEntity = Topic.class, mappedBy = "majorId")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Collection<Topic> topics;

    @Builder
    public Major(String id, String name, String description, Collection<Student> students, Collection<Topic> topics) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.students = students;
        this.topics = topics;
    }
    
}
