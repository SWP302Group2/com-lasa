/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lasa.data.repo.repository;

import com.lasa.data.model.entity.Lecturer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

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

   /* @Override
    @EntityGraph(attributePaths = {"students.student", "slots"})
    Page<Lecturer> findAll(Specification<Lecturer> specification, Pageable  pageable);*/
}
