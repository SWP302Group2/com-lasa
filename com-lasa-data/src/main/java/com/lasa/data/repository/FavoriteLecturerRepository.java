/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lasa.data.repository;

import java.util.List;

import com.lasa.data.entity.FavoriteLecturer;
import com.lasa.data.entity.key.FavoriteLecturerKey;
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
public interface FavoriteLecturerRepository extends JpaRepository<FavoriteLecturer, FavoriteLecturerKey> {
    
    @Query("FROM FavoriteLecturer f JOIN FETCH f.lecturer JOIN FETCH f.student")
    List<FavoriteLecturer> findAllLecturerAndStudentInFavoriteLecturer();

}
