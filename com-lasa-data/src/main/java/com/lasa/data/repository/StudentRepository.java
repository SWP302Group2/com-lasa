/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lasa.data.repository;

import java.util.List;
import java.util.Optional;

import com.lasa.data.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author hai
 */
@Repository
public interface StudentRepository extends JpaRepository<Student, Integer> {
    
    public Optional<Student> findByName(String name);
    
    public void deleteByIdIn(List<Integer> ids);

    public Optional<Student> findStudentByEmail(String email);
    
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
