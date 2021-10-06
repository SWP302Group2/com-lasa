/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lasa.data.repository;

import java.util.Optional;

import com.lasa.data.entity.Lecturer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 *
 * @author hai
 */
@Repository
public interface LecturerRepository extends JpaRepository<Lecturer, Integer> {

    public Optional<Lecturer> findByName(String name);

    public Optional<Lecturer> findLecturerByEmail(String email);

    @Query("SELECT f.id, f.name, f.avatarUrl, f.gender, f.email FROM Lecturer f")
    Page<Lecturer> findBasicInformationLecturers(Pageable pageable);

}
