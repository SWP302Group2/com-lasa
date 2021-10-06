/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lasa.data.entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import java.io.Serializable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;

import com.lasa.data.entity.key.LecturerTopicDetailKey;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author hai
 */
@Entity
@Table(name = "lecturertopicdetail")
@Data
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_EMPTY)
@JsonIdentityInfo(generator=ObjectIdGenerators.IntSequenceGenerator.class, property="@id")
public class LecturerTopicDetail implements Serializable {
    
    @JsonIgnore
    @EmbeddedId
    private LecturerTopicDetailKey id = new LecturerTopicDetailKey();
    
    @ManyToOne(targetEntity = Lecturer.class, fetch = FetchType.LAZY)
    @MapsId("lecturerId")
    @JoinColumn(name = "lecturerid")
    private Lecturer lecturer;

    @ManyToOne(targetEntity = Topic.class, fetch = FetchType.LAZY)
    @MapsId("topicId")
    @JoinColumn(name = "topicid")
    private Topic topic;

    @Builder
    public LecturerTopicDetail(Lecturer lecturer, Topic topic) {
        this.lecturer = lecturer;
        this.topic = topic;
    }

}
