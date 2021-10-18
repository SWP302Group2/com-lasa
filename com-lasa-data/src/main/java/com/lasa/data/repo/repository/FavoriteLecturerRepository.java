/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lasa.data.repo.repository;

import com.lasa.data.repo.customrepository.FavoriteLecturerCustomRepository;
import com.lasa.data.entity.FavoriteLecturer;
import com.lasa.data.entity.key.FavoriteLecturerKey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 *
 * @author hai
 */
@Repository
public interface FavoriteLecturerRepository extends JpaRepository<FavoriteLecturer, FavoriteLecturerKey>, JpaSpecificationExecutor<FavoriteLecturer>, FavoriteLecturerCustomRepository {

    @Query("FROM FavoriteLecturer f JOIN FETCH f.lecturer JOIN FETCH f.student")
    List<FavoriteLecturer> findAllLecturerAndStudentInFavoriteLecturer();

}
