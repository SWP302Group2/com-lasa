/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lasa.business.controllers;

import com.lasa.data.entity.Lecturer;
import com.lasa.data.entity.LecturerPage;
import com.lasa.data.entity.LecturerSearchCriteria;
import java.util.List;
import org.springframework.data.domain.Page;

import com.lasa.data.page.LecturerPage;
import com.lasa.data.searchcriteria.LecturerSearchCriteria;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 *
 * @author hai
 */
@RequestMapping("/default")
public interface LecturerOperations {
    
    @GetMapping
    public List<Lecturer> findAll();

    @GetMapping("/basic-information")
    ResponseEntity<?> findBasicInformationLecturers(
            @RequestParam(value = "page", required = false, defaultValue = "0") Integer page,
            @RequestParam(value = "size", required = false, defaultValue = "10") Integer size
    );

    @GetMapping("/get")
    public ResponseEntity<Page<Lecturer>> getLecturer(LecturerPage lecturerPage,
                                                      LecturerSearchCriteria lecturerSearchCriteria);
    
    @GetMapping("/{id}")
    public Lecturer findByLecturerId(@PathVariable("id") Integer id);
    
    @PostMapping
    public Lecturer createLecturer(@RequestBody Lecturer lecturer);
    
    @PutMapping
    public Lecturer updateLecturer(@RequestBody Lecturer lecturer);
    
    @DeleteMapping
    public void deleteLecturers(List<Integer> ids);
    
    @GetMapping("/get")
    public ResponseEntity<Page<Lecturer>> getLecturer(LecturerPage lecturerPage,
                                                      LecturerSearchCriteria lecturerSearchCriteria);
}
