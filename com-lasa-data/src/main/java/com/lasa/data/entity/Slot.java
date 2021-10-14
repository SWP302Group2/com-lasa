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
import java.time.LocalDateTime;
import java.util.Collection;
import javax.persistence.*;

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
@Table(name = "slot")
@Data
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_EMPTY)
@JsonIdentityInfo(generator=ObjectIdGenerators.IntSequenceGenerator.class, property="@id", scope = Slot.class)
public class Slot implements Serializable {
    
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "lecturerid")
    private String lecturerId;

    @OneToMany(targetEntity = BookingRequest.class, mappedBy = "slotId")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Collection<BookingRequest> bookingRequests;

    @OneToMany(targetEntity = SlotTopicDetail.class,mappedBy = "slot")
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Collection<SlotTopicDetail> topics;

    @Column(name = "timestart")
    private LocalDateTime timeStart;
    
    @Column(name = "timeend")
    private LocalDateTime timeEnd;

    @Builder
    public Slot(Integer id, String lecturerId, Collection<BookingRequest> bookingRequests, Collection<SlotTopicDetail> topics, LocalDateTime timeStart, LocalDateTime timeEnd) {
        this.id = id;
        this.lecturerId = lecturerId;
        this.bookingRequests = bookingRequests;
        this.topics = topics;
        this.timeStart = timeStart;
        this.timeEnd = timeEnd;
    }
}
