/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lasa.data.repo.repository;

import com.lasa.data.model.entity.Student;
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
public interface StudentRepository extends JpaRepository<Student, Integer>, JpaSpecificationExecutor<Student> {
    
    Optional<Student> findByName(String name);
    
    void deleteByIdIn(List<Integer> ids);

    Optional<Student> findStudentByEmail(String email);

    @Query("select count(s) from Student s where s.id in ?1 and (s.status = 0 or s.status = 1 or s.status = -1)")
    long countAvailableForDelete(List<Integer> id);
    
//    @Query("FROM Student s JOIN FETCH s.major WHERE s.id = :id")
//    public Optional<Student> findByIdAndGetMajor(@Param("id") Integer id);
//    
//    @Query("FROM Student s JOIN FETCH s.favoriteLecturer f JOIN FETCH f.lecturer  WHERE s.id = :id")
//    public Optional<Student> findByIdAndGetFavoriteLecturer(@Param("id") Integer id);
//    
//    
//    @EntityGraph(value = "graph.Student.bookingRequests")
//    @Override
//    public Optional<Student> findById(Integer id);
    
//    @Query("FROM Student s WHERE s.id = :id")
//    @EntityGraph(value = "graph.Student.bookingRequests")
//    public Optional<Student> findAllInfo(@Param("id") Integer id);
//    
////    @EntityGraph(value = "graph.Student.bookingRequests")
//    @Override
//    public List<Student> findAll();
}
