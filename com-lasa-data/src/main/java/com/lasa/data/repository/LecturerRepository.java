/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lasa.data.repository;

import java.util.List;
import java.util.Optional;

import com.lasa.data.entity.Lecturer;
import com.lasa.data.entity.predicate.LecturerSpecifications;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.Predicate;


/**
 *
 * @author hai
 */
@Repository
public interface LecturerRepository extends JpaRepository<Lecturer, Integer>, JpaSpecificationExecutor<Lecturer> {

    public Optional<Lecturer> findByName(String name);

    public Optional<Lecturer> findLecturerByEmail(String email);

   /* @Override
    @EntityGraph(attributePaths = {"students.student", "slots"})
    Page<Lecturer> findAll(Specification<Lecturer> specification, Pageable  pageable);*/
}
