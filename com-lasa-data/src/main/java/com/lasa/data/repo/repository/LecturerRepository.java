/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lasa.data.repo.repository;

import com.lasa.data.model.entity.Lecturer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


/**
 *
 * @author hai
 */
@Repository
public interface LecturerRepository extends JpaRepository<Lecturer, Integer>, JpaSpecificationExecutor<Lecturer> {

    Optional<Lecturer> findByName(String name);

    Optional<Lecturer> findLecturerByEmail(String email);

    long countById(Integer id);

    @Query("select count(l) from Lecturer l where l.id in ?1 and (l.status = 0 or l.status = 1 or l.status = -1)")
    long countAvailableForDelete(List<Integer> id);

    @Query("Select count(l) from Lecturer l where l.id in ?1 and l.status = 1")
    long countActiveLecturers(List<Integer> id);

   /* @Override
    @EntityGraph(attributePaths = {"students.student", "slots"})
    Page<Lecturer> findAll(Specification<Lecturer> specification, Pageable  pageable);*/
}
